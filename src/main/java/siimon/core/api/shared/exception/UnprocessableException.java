package siimon.core.api.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableException extends RuntimeException {

	private String type = "unprocessableEntity";
	public UnprocessableException(String message) {
		super(message);
	}

	public UnprocessableException(String message, String type) {
		super(message);
		this.type = type;
	}

	public UnprocessableException(String message, Throwable cause) {
		super(message, cause);
	}

}
