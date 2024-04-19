package siimon.core.api.module.testid.mapper;

import siimon.core.api.module.testid.dto.addNewTest.AddNewTestQuestionDto;
import siimon.core.api.module.testid.model.QuestionModel;
import siimon.core.api.shared.util.DateUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionMapper {

	public static Set<QuestionModel> mapToModel_saveAll(List<AddNewTestQuestionDto> dto) {
		Set<QuestionModel> questionSet = new HashSet<>() { };
		for (AddNewTestQuestionDto questionDto : dto) {
			questionSet.add(QuestionModel.builder()
				  .maxTime(DateUtil.stringToLocalTime(questionDto.getMaxTime()))
				  .question(questionDto.getQuestion())
				  .points(questionDto.getPoints())
				  .answers(AnswerMapper.mapToModel_saveAll(questionDto.getAnswers()))
				  .build()
			);
		}

		return questionSet;
	}


}
