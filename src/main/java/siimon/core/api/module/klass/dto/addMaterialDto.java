package siimon.core.api.module.klass.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class addMaterialDto {

	@NotNull
	@Size(min = 2, max = 500)
	private String title;

	@NotNull
	@Size(min = 2, max = 500)
	private String description;

	@Size
	private Map<String, Object> links;

	@Size(max = 12)
	private String deadline;

}
