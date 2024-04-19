package siimon.core.api.module.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import siimon.core.api.module.auth.dto.LoginRequestDto;
import siimon.core.api.module.auth.service.IUserSessionService;
import siimon.core.api.shared.dto.RespDto;
import siimon.core.api.shared.exception.NotFoundException;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserAuthController {

	private final IUserSessionService service;

	@PostMapping("/login")
	public RespDto login(@RequestBody @Valid LoginRequestDto loginDto, HttpServletRequest request) {
		log.info("Started login process for user: " + loginDto.getUsername());
		return service.login(loginDto, request);
	}

	@PostMapping("/logout")
	public RespDto logout(HttpServletRequest request, HttpServletResponse response) {
		var auth = SecurityContextHolder.getContext().getAuthentication();
		log.info("Started logout process for user: " + auth);
		return service.logout(auth, request, response);
	}

	@PostMapping(value = "/data")
	public RespDto getCurrentUserData() {
		var auth = SecurityContextHolder.getContext().getAuthentication();
		log.info("Getting current user auth data for user: " + auth);

		if (auth instanceof AnonymousAuthenticationToken) {
			throw new NotFoundException("No user logged in", "noUserLoggedIn");
		}

		return new RespDto("currentUserAuth", "Current user auth data", "/v2/auth/data", auth);

	}

}
