package siimon.core.api.shared.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.thymeleaf.exceptions.TemplateInputException;
import siimon.core.api.shared.dto.RespDto;

@Log4j2
@ControllerAdvice
// 400, 403, 404, 405, 415, 500
// https://www.baeldung.com/exception-handling-for-rest-with-spring
public class ControllerExceptionAdvice {

	@ResponseBody
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public RespDto handleUnsupportedMediaTypeException(HttpMediaTypeNotSupportedException e, HttpServletRequest req) {
		log.error("Invalid media type: " + e.getMessage());
		return new RespDto(
				"invalidMediaType",
				e.getMessage(),
				req.getRequestURI()
		);
	}


	@ResponseBody
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public RespDto handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest req) {
		log.error("Invalid req method type: " + e.getMessage());
		return new RespDto(
				"invalidReqMethodType",
				e.getMessage(),
				req.getRequestURI()
		);
	}


	@ResponseBody
	@ExceptionHandler({NotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public RespDto handleNotFoundException(NotFoundException e, HttpServletRequest req) {
		log.error("Request URL: " + req.getRequestURL() + " raised " + e);
		return new RespDto(
				e.getType(),
				e.getMessage(),
				req.getRequestURI()
		);
	}

	@ResponseBody
	@ExceptionHandler({UsernameNotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public RespDto handleUsernameNotFoundException(UsernameNotFoundException e, HttpServletRequest req) {
		log.error("Request URL: " + req.getRequestURL() + " raised " + e);
		return new RespDto(
				"usernameNotFound",
				e.getMessage(),
				req.getRequestURI()
		);
	}


	@ResponseBody
	@ExceptionHandler({BadReqException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RespDto handleBadReqException(BadReqException e, HttpServletRequest req) {
		log.error("Request URL: " + req.getRequestURL() + " raised " + e);
		return new RespDto(
				e.getType(),
				e.getMessage(),
				req.getRequestURI()
		);
	}

	@ResponseBody
	@ExceptionHandler({BadCredentialsException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RespDto handleBadCredentialsException(Exception e, HttpServletRequest req) {
		log.error("Request URL: " + req.getRequestURL() + " raised " + e);
		return new RespDto(
				(e.getClass() == BadCredentialsException.class ? "badCredentials" : "usernameNotFound"),
				e.getMessage(),
				req.getRequestURI()
		);
	}

	@ResponseBody
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RespDto handleMethodArgumentNotValidException(Exception e, HttpServletRequest req) {
		log.error("Request URL: " + req.getRequestURL() + " raised " + e);
		return new RespDto(
				"badRequest",
				e.getMessage(),
				req.getRequestURI()
		);
	}

	@ResponseBody
	@ExceptionHandler({AccessDeniedException.class, TemplateInputException.class,
			SecurityException.class, HttpClientErrorException.Forbidden.class})
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public RespDto handleAccessDeniedException(Exception e, HttpServletRequest req) {
		log.error("Request URL: " + req.getRequestURL() + " raised " + e);
		return new RespDto(
				"accessDenied",
				e.getMessage(),
				req.getRequestURI()
		);
	}

	@ResponseBody
	@ExceptionHandler({Exception.class, NoClassDefFoundError.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public RespDto handleException(Exception e, HttpServletRequest req) {
		log.error("Request URL: " + req.getRequestURL() + " raised " + e);
		return new RespDto(
				"internalServerError",
				e.getMessage(),
				req.getRequestURI()
		);
	}

}
