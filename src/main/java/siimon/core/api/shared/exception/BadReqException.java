package siimon.core.api.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadReqException extends RuntimeException {

	private String type = "badRequest";

	public BadReqException(String message) {
		super(message);
	}

	public BadReqException(String message, String type) {
		super(message);
		this.type = type;
	}

	public BadReqException(String message, Throwable cause) {
		super(message, cause);
	}


}
