package siimon.core.api.module.testid.mapper;

import siimon.core.api.module.testid.dto.addNewTest.AddNewTestAnswerDto;
import siimon.core.api.module.testid.model.AnswerModel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnswerMapper {

	public static Set<AnswerModel> mapToModel_saveAll(List<AddNewTestAnswerDto> dto) {
		Set<AnswerModel> answerSet = new HashSet<>();

		for (AddNewTestAnswerDto answerDto: dto) {
			answerSet.add(AnswerModel.builder()
				.answer(answerDto.getAnswer())
				.correct(answerDto.getCorrect())
				.build()
			);
		}

		return answerSet;
	}

}
