package siimon.core.api.module.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import siimon.core.api.module.auth.dto.LoginRequestDto;
import siimon.core.api.shared.dto.RespDto;

public interface IUserSessionService {

	RespDto login(LoginRequestDto loginRequestDto, HttpServletRequest request);
	RespDto logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response);

}
