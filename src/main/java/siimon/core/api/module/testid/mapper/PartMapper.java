package siimon.core.api.module.testid.mapper;

import siimon.core.api.module.testid.dto.addNewTest.AddNewTestPartDto;
import siimon.core.api.module.testid.model.PartModel;
import siimon.core.api.shared.util.DateUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PartMapper {

	public static Set<PartModel> mapToModel_saveAll(List<AddNewTestPartDto> dto) {
		Set<PartModel> partSet = new HashSet<>();

		for (AddNewTestPartDto addNewTestPartDto : dto) {
			partSet.add(PartModel.builder()
				.description(addNewTestPartDto.getDescription())
				.maxTime(DateUtil.stringToLocalTime(addNewTestPartDto.getMaxTime()))
				.step(addNewTestPartDto.getStep())
				.questions(QuestionMapper.mapToModel_saveAll(addNewTestPartDto.getQuestions()))
				.build()
			);
		}

		return partSet;
	}

}
