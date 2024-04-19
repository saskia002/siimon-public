package siimon.core.api.module.klass.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewClassTeacherDto {

	@NotNull
	@Min(0)
	@Max(Integer.MAX_VALUE/2)
	private Integer id;

}
