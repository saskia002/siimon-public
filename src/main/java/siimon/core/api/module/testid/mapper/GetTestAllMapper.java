package siimon.core.api.module.testid.mapper;

import siimon.core.api.module.testid.dto.getTestAll.GetAllTestAnswerDto;
import siimon.core.api.module.testid.dto.getTestAll.GetAllTestPartDto;
import siimon.core.api.module.testid.dto.getTestAll.GetAllTestQuestionDto;
import siimon.core.api.module.testid.dto.getTestAll.GetAllTestTestDto;
import siimon.core.api.module.testid.model.AnswerModel;
import siimon.core.api.module.testid.model.QuestionModel;
import siimon.core.api.module.testid.model.TestModel;
import siimon.core.api.module.testid.model.TestPartModel;
import siimon.core.api.shared.util.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GetTestAllMapper {

	public static GetAllTestTestDto mapToGetAllTestDto(TestModel test) {
		return GetAllTestTestDto.builder()
				.key(test.getProtectedKey())
				.title(test.getTitle())
				.description(test.getDescription())
				.build();
	}

	public static GetAllTestPartDto mapToGetAllTestPartDto(TestPartModel testPartModel, Integer stepCount) {
		var part = testPartModel.getPart();

		return GetAllTestPartDto.builder()
				.id(part.getId())
				.description(part.getDescription())
				.maxTime(DateUtil.localTimeToString(part.getMaxTime()))
				.step(part.getStep())
				.questions(mapToGetAllTestPartDtoList(part.getQuestions()))
				.stepCount(stepCount)
				.build();

	}

	public static List<GetAllTestQuestionDto> mapToGetAllTestPartDtoList(Set<QuestionModel> testPartModels) {
		List<GetAllTestQuestionDto> questions = new ArrayList<>();

		for (QuestionModel questionModel : testPartModels ) {
			questions.add(mapToGetAllTestQuestionDto(questionModel));
		}

		return questions;
	}

	private static GetAllTestQuestionDto mapToGetAllTestQuestionDto(QuestionModel questionModel) {
		return GetAllTestQuestionDto.builder()
				.id(questionModel.getId())
				.question(questionModel.getQuestion())
				.points(questionModel.getPoints())
				.answers(mapToGetAllTestAnswerDtoList(questionModel.getAnswers()))
				.build();
	}

	private static List<GetAllTestAnswerDto> mapToGetAllTestAnswerDtoList(Set<AnswerModel> answerModels) {
		List<GetAllTestAnswerDto> answers = new ArrayList<>();

		for (AnswerModel answerModel : answerModels ) {
			answers.add(mapToGetAllTestAnswerDto(answerModel));
		}

		return answers;
	}

	private static GetAllTestAnswerDto mapToGetAllTestAnswerDto(AnswerModel answerModel) {
		return GetAllTestAnswerDto.builder()
				.id(answerModel.getId())
				.answer(answerModel.getAnswer())
				.build();
	}


}
