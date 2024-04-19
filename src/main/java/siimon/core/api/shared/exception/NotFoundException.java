package siimon.core.api.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	private String type = "notFound";

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, String type) {
		super(message);
		this.type = type;
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
