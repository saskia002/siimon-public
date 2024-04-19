package siimon.core.api.module.avaleht.dto.courseReg;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddCourseRegPersonDto {

	@Size(min = 1, max = 500, message = "Full name is required")
	private String fullName;

	@Size(min = 1, max = 500, message = "Mail is required")
	private String mail;

	@Size(min = 1, max = 255, message = "Phone is required")
	private String phone;

	@Size(min = 1, max = 10, message = "Id code is required")
	private String idCode;

}
