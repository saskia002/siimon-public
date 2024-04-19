package siimon.core.api.module.testid.mapper;

import siimon.core.api.module.testid.dto.addNewTest.AddNewTestTestDto;
import siimon.core.api.module.testid.model.TestModel;

public class TestMapper {

	public static TestModel mapToModel(AddNewTestTestDto dto) {
		return TestModel.builder()
				.protectedKey(dto.getKey())
				.title(dto.getTitle())
				.description(dto.getDescription())
				.build();
	}

}
