package siimon.core.api.module.avaleht.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDto {

	@Min(value = 0, message = "Id must be set")
	@Max(value = 1000000000, message = "Id must be less than 1000000000")
	private Integer id;

	@Size(min = 1, max = 500, message = "Name is required")
	private String fullName;

	@Size(min = 1, max = 500, message = "Mail is required")
	private String mail;

	@Size(min = 1, max = 255, message = "Phone is required")
	private String phone;

	@NotNull(message = "Id code is required")
	@Min(value = 1, message = "Id code must be greater than 0")
	@Max(value = 1000000000, message = "Id code must be less than 1000000000")
	private String idCode;

}
