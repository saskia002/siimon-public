package siimon.core.api.module.klass.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDto {

	@Min(0)
	@Max(Integer.MAX_VALUE/2)
	private Integer id;

	private MaterialBaseDto material;

	@Size(max = 12)
	private String deadline;

}
