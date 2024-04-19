package siimon.core.api.module.testid.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import siimon.core.api.module.testid.dto.GradeInfoDto;
import siimon.core.api.module.testid.dto.TestSubmitDto;
import siimon.core.api.module.testid.dto.TestSubmitRespDto;
import siimon.core.api.module.testid.dto.getTestAll.GetAllTestDto;
import siimon.core.api.module.testid.dto.saveUserResult.SaveUserResultDto;
import siimon.core.api.module.testid.dto.saveUserResult.SaveUserResultRespDto;
import siimon.core.api.module.testid.mapper.GetTestAllMapper;
import siimon.core.api.module.testid.mapper.PersonMapper;
import siimon.core.api.module.testid.model.GradeModel;
import siimon.core.api.module.testid.model.ResultModel;
import siimon.core.api.module.testid.repository.*;
import siimon.core.api.module.testid.util.EmailTemplate;
import siimon.core.api.shared.EmailService;
import siimon.core.api.shared.dto.RespDto;
import siimon.core.api.shared.exception.NotFoundException;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TestidUserUserService implements ITestidUserService {

	private final TPersonRepository personRepository;
	private final TestRepository testRepository;
	private final PartRepository partRepository;
	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	private final TestPartRepository testPartRepository;
	private final GradeRepository gradeRepository;
	private final EmailService emailService;
	private final LangLevelRepository langLevelRepository;
	private final ResultRepository resultRepository;


	@Transactional
	public RespDto addNewPersonTestSubmit(TestSubmitDto testSubmitDto, String key) {
		testRepository.findByProtectedKey(key.toUpperCase())
				.orElseThrow(()-> new NotFoundException("Test not found", "testNotFound"));

		var newPerson = personRepository.save(PersonMapper.mapToModel(testSubmitDto));

		System.out.println("New person added: " + newPerson.toString());

		var respData = TestSubmitRespDto.builder()
						.userId(newPerson.getId())
						.key(key)
						.build();

		return new RespDto("addNewPersonTest", "Test with key " + key + " found, new person added",
						   "/v2/testid", respData);
	}

	@Transactional(readOnly = true)
	public RespDto getTestByKeyAndStep(String key, Integer step) {
		key = key.toUpperCase();
		GetAllTestDto returnTest = new GetAllTestDto();
		var partsCount = getPartCountByKey(key);

		var testFromDB = testRepository.findByProtectedKey(key)
				.orElseThrow(()-> new NotFoundException("Test not found", "testNotFound"));

		var testPartFromDB = testPartRepository.findByTestIdAndPartStep(testFromDB.getId(), step)
				.orElseThrow(()-> new NotFoundException("Test parts not found", "testPartsNotFound"));

		var part = partRepository.findByIdAndStep(testPartFromDB.getPart().getId(), step)
				.orElseThrow(()-> new NotFoundException("Part not found", "partNotFound"));

		var questions = questionRepository.findByPartId(part.getId())
				.orElseThrow(()-> new NotFoundException("Questions not found", "questionsNotFound"));

		for (var question : questions) {
			var answers = answerRepository.findByQuestionId(question.getId())
					.orElseThrow(()-> new NotFoundException("Answers not found", "answersNotFound"));

			question.setAnswers(answers);
		}

		part.setQuestions(questions);
		testPartFromDB.setPart(part);
		returnTest.setPart(GetTestAllMapper.mapToGetAllTestPartDto(testPartFromDB, partsCount));
		returnTest.setTest(GetTestAllMapper.mapToGetAllTestDto(testFromDB));


		return new RespDto("getTestByKeyAndStep","Test with key " + key + " found",
						   "/v2/testid", returnTest);
	}

	@Transactional(readOnly = true)
	private Integer getPartCountByKey(String testKey) {
		return testPartRepository.findByTestProtectedKey(testKey)
				.orElseThrow(()-> new NotFoundException("Test parts not found", "testPartsNotFound"))
				.size();
	}

	@Transactional
	public RespDto saveUserResult(SaveUserResultDto saveUserResultDto, String key, Integer step) {
		key = key.toUpperCase();
		var testFromDb = testRepository.findByProtectedKey(key)
				.orElseThrow(()-> new NotFoundException("Test not found", "testNotFound"));

		var partFromDb = testPartRepository.findByTestIdAndPartStep(testFromDb.getId(), step)
				.orElseThrow(()-> new NotFoundException("Part not found", "partNotFound"));
		var partId = partFromDb.getId();

		// * Max points for test part
		double maxResult = getPartMaxPoints(partId);

		// get part total count
		var partsCount = testPartRepository.findByTestId(testFromDb.getId())
				.orElseThrow(()-> new NotFoundException("Test parts not found", "testPartsNotFound"))
				.size();

		// * Total points for user answers
		var totalResult = checkQuestionAnswers(saveUserResultDto.getAnswers());
		int resultTotalPercentage = (int) Math.ceil((totalResult / maxResult) * 100);

		// * Final grade for user
		var finalGrade = getLangResultLevel(resultTotalPercentage, partId);

		// * Check for next part
		boolean nextPart = partsCount > step && finalGrade.getNextPart();

        var person = personRepository.findById(saveUserResultDto.getUserId())
				.orElseThrow(()-> new NotFoundException("Person not found", "personNotFound"));

		// * Save result to DB and return result
		resultRepository.save(
				ResultModel.builder()
						.testPart(partFromDb)
						.langLevel(langLevelRepository.findById(finalGrade.getLangLevelId())
										   .orElseThrow(()-> new NotFoundException("Lang level not found", "langLevelNotFound")))
						.person(person)
						.answers(saveUserResultDto.getAnswers())
						.totalPoints(totalResult)
						.maxPoints(maxResult)
						.build()
		);

		var resultDto = SaveUserResultRespDto.builder()
				.langLevel(finalGrade.getLangLevel())
				.resultPercentage(resultTotalPercentage)
				.nextPart(nextPart)
				.nextPartNr(nextPart ? step + 1 : null)
				.build();

		// * send mail to admin when user finishes the test
		if(nextPart) {
			emailService.sendHtmlEmail(
					"????????????@??????.ee",
					"Testi tulemus",
						EmailTemplate.genTestidEmailTemplate(
							person.getFullName(),
							finalGrade.getLangLevel(),
							resultTotalPercentage
					)
			);
		}

		return new RespDto("saveUserResult", "Result saved successfully", "/v2/testid", resultDto);
	}

	@Transactional(readOnly = true)
	private double getPartMaxPoints(Integer partId) {
		double maxPoints = 0.0;

		var questions = questionRepository.findByPartId(partId)
				.orElseThrow(()-> new NotFoundException("Questions not found", "questionsNotFound"));

		for (var question : questions) {
			maxPoints += question.getPoints();
		}

		return maxPoints;
	}

	@Transactional(readOnly = true)
	private double checkQuestionAnswers(Map<String, Integer> questionAnswers) {
		double totalResult = 0.0;

		for (Map.Entry<String, Integer> entry : questionAnswers.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();

			var answer = answerRepository.findByIdAndQuestionId(value, Integer.valueOf(key))
					.orElseThrow(()-> new NotFoundException("Answer not found", "answerNotFound"));

			var question = answer.getQuestion();

			if (answer.getCorrect()) {
				totalResult += question.getPoints();
			}

		}

		return totalResult;
	}

	@Transactional(readOnly = true)
	public GradeInfoDto getLangResultLevel(double resultTotalPercentage, Integer partId) {
		var grade = gradeRepository.findByPartId(partId)
				.orElseThrow(()-> new NotFoundException("Grade not found", "gradeNotFound"));

		for (GradeModel gradeModel: grade) {
			if (resultTotalPercentage >= gradeModel.getMinResultPercent().doubleValue() &&
					resultTotalPercentage <= gradeModel.getMaxResultPercent().doubleValue()) {
				return new GradeInfoDto(
						gradeModel.getLangLevel().getId(),
						gradeModel.getLangLevel().getValue(),
						gradeModel.getNextPart()
				);
			}
		}

		return new GradeInfoDto(null, "", false);
	}

	@Transactional(readOnly = true)
	public boolean getTestPartNextStep(Integer testId, Integer step) {
		return testPartRepository.findByTestIdAndPartStep(testId, step + 1).isPresent();
	}

}
