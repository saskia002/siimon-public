package siimon.core.api.module.klass.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class MaterialBaseDto {

	@Min(0)
	@Max(Integer.MAX_VALUE / 2)
	private Integer id;

	@Min(0)
	@Max(Integer.MAX_VALUE/2)
	private Integer courseMaterialId;

	@Size(min = 2, max = 500)
	private String title;

	@Size(min = 2, max = 500)
	private String description;

	@Size
	private Map<String, Object> links;

	@Size(max = 12)
	private String deadline;

}
