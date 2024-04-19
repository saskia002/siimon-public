package siimon.core.api.module.auth.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

	@Size(max = 50)
	private String username;

	@Size(max = 500)
	private String password;

}
