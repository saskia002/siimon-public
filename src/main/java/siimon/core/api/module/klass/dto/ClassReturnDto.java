package siimon.core.api.module.klass.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassReturnDto {

	@NotNull
	@Size(max = 255)
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

	private LinkedHashSet<MaterialBaseDto> materials;

	private LinkedHashSet<TeacherBaseDto> teachers;

}
