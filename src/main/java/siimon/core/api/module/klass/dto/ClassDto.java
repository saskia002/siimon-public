package siimon.core.api.module.klass.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassDto {

	@Min(0)
	@Max(Integer.MAX_VALUE/2)
	private Integer id;

	@NotNull
	@Size(max = 255)
	private String key;

	@NotNull
	@Size(max = 1000)
	private String title;

	@NotNull
	@Size(max = 2555)
	private String description;

	@NotNull
	@Size(max = 12)
	private String begins;

	@NotNull
	@Size(max = 12)
	private String ends;

	@Size(max = 1000)
	private String schedule;

	@Size(max = 1000)
	private String location;

	@Size(max = 1000)
	private String meets;

	@Min(0)
	@Max(Integer.MAX_VALUE/2)
	private Integer teacherId;

}
