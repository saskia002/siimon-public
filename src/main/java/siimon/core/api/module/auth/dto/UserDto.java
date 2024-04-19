package siimon.core.api.module.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

	private UserNameDto username;
	private UserPasswordDto password;
	private List<AuthorityDto> authorities;

}
