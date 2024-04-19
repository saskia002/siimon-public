package siimon.core.api.module.testid.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestSubmitDto {

	@NotNull
	@Size(min = 2, max = 500)
	private String fullName;

	@Size(min = 2, max = 255)
	private String phone;

	@Size(min = 2, max = 255)
	private String email;

}
