package siimon.core.api.module.avaleht.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CourseRegDto {


	@Min(value = 0, message = "Id must be set")
	@Max(value = 1000000000, message = "Id must be less than 1000000000")
	private Integer id;

	@Size(message = "Timestamp is required")
	private Instant created;

	@Size(message = "course is required")
	private CourseDto course;

	@Size(message = "person is required")
	private PersonDto person;

}
