package siimon.core.api.module.auth.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import siimon.core.api.module.auth.dto.LoginRequestDto;
import siimon.core.api.module.auth.service.auth.UserAuthService;
import siimon.core.api.shared.dto.RespDto;
import siimon.core.api.shared.exception.BadReqException;
import siimon.core.api.shared.exception.NotFoundException;
import siimon.core.api.shared.util.PasswordEncoderUtil;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserSessionService implements IUserSessionService {

	private final UserAuthService userDetailsService;
	private final AuthenticationManager authenticationManager;
	SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

	public RespDto login(LoginRequestDto loginRequestDto, HttpServletRequest request) {
		var principal = userDetailsService.loadUserByUsername(loginRequestDto.getUsername());
		if(principal == null) {
			throw new BadReqException("Invalid username", "invalidUsername");
		}
		if(!PasswordEncoderUtil.matches(loginRequestDto.getPassword(), principal.getPassword())) {
			throw new BadReqException("Invalid password", "invalidPassword");
		}
		var token = new UsernamePasswordAuthenticationToken(
				principal, principal.getPassword(), principal.getAuthorities()
		);

		var context = SecurityContextHolder.getContext();
		context.setAuthentication(token);

		HttpSession session = request.getSession(true);
		session.setAttribute("SPRING_SECURITY_CONTEXT", context);

		return new RespDto("loginUser", "Login successful", "/v2/auth/login", context.getAuthentication());
	}

	public RespDto logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		if (authentication instanceof AnonymousAuthenticationToken) {
			throw new NotFoundException("No user logged in", "noUserLoggedIn");
		}

		logoutHandler.logout(request, response, authentication);

		try {
			HttpSession session = request.getSession(true);
			session.removeAttribute("SPRING_SECURITY_CONTEXT");
			session.invalidate();

			// remove cookies
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					cookie.setMaxAge(0);
					cookie.setValue(null);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}

		} catch (Exception e) {
			log.warn("Session already invalidated", e);
		}

		return new RespDto("logoutUser", "Logout successful",
						   "/v2/auth/logout", SecurityContextHolder.getContext().getAuthentication());
	}

}
