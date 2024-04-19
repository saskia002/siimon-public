package siimon.core.api.module.avaleht.dto.courseReg;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddCourseRegDto {

	@NotNull(message = "Id is required")
	@Min(value = 1, message = "Id must be greater than 0")
	@Max(value = 1000000000, message = "Id must be less than 1000000000")
	private Integer courseId;

	private AddCourseRegPersonDto person;

}
