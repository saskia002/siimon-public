package siimon.core.api.module.testid.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import siimon.core.api.module.testid.dto.addNewTest.AddNewTestAllDto;
import siimon.core.api.module.testid.mapper.PartMapper;
import siimon.core.api.module.testid.mapper.TestMapper;
import siimon.core.api.module.testid.model.TestPartModel;
import siimon.core.api.module.testid.repository.*;
import siimon.core.api.shared.dto.RespDto;
import siimon.core.api.shared.exception.BadReqException;

@Service
@RequiredArgsConstructor
public class TestidAdminService implements ITestidAdminService {

	private final TestRepository testRepository;
	private final PartRepository partRepository;
	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	private final TestPartRepository testPartRepository;

	@Transactional
	public RespDto addNewTestWithAllData(AddNewTestAllDto addNewTestAllDto) {
		testRepository.findByProtectedKey(addNewTestAllDto.getTest().getKey())
			.ifPresent(test -> {
				throw new BadReqException("Test with key " + addNewTestAllDto.getTest().getKey() + " already exists",
										  "testAlreadyExists");
			});

		var newTest = testRepository.save(TestMapper.mapToModel(addNewTestAllDto.getTest()));
		var partSet = PartMapper.mapToModel_saveAll(addNewTestAllDto.getParts());

		for (var part : partSet) {
			var newPart = partRepository.save(part);
			testPartRepository.save(TestPartModel.builder()
										.test(newTest)
										.part(newPart)
										.build()
			);
			for (var question : newPart.getQuestions()) {
				question.setPart(newPart);
				var newQuestion = questionRepository.save(question);
				for (var answer : newQuestion.getAnswers()) {
					answer.setQuestion(newQuestion);
					answerRepository.save(answer);
				}
			}

		}

		return new RespDto("addNewTestWithAllData", "Test added successfully", "/v2/testid/all");
	}

}
