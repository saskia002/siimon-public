package siimon.core.api.module.avaleht.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Builder
public class CourseDto {

	@Min(value = 0, message = "Id must be set")
	@Max(value = 1000000000, message = "Id must be less than 1000000000")
	private Integer id;

	@Size(min = 1, max = 1000, message = "titleReg is required")
	private String titleRegistration;

	@Size(min = 1, max = 2555, message = "title is required")
	private String title;

	@Size(min = 1, max = 2555, message = "description is required")
	private String description;

	@Size(min = 1, max = 255, message = "date is required")
	private String date;

	@JsonProperty("links")
	private Object links;

}
