package siimon.core.api.module.testid.mapper;

import siimon.core.api.module.testid.dto.TestSubmitDto;
import siimon.core.api.module.testid.model.TPersonModel;

public class PersonMapper {

	public static TPersonModel mapToModel(TestSubmitDto testSubmitDto) {
		return TPersonModel.builder()
				.fullName(testSubmitDto.getFullName())
				.email(testSubmitDto.getEmail())
				.phone(testSubmitDto.getPhone())
				.build();
	}

}
