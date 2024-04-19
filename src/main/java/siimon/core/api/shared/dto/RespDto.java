package siimon.core.api.shared.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Log4j2
public class RespDto {

	private String title;
	private String type;
//	private Integer status;
//	private String detail;
	private String instance;
	private String timestamp;

	@JsonIgnoreProperties(value = { "password", "credentials", "details", "accountNonExpired",
									"credentialsNonExpired", "accountNonLocked", "authorities", "enabled" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object data;

	public RespDto(String type, String title, String instance) {
		this.type = type;
		this.title = title;
		this.instance = instance;
		timestamp = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS").format(LocalDateTime.now());
		log.info("Sending response: " + this);
	}

	public RespDto(String type, String title, String instance, Object data) {
		this.type = type;
		this.title = title;
		this.instance = instance;
		this.data = data;
		timestamp = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS").format(LocalDateTime.now());
		log.info("Sending response: " + this);
	}

}
