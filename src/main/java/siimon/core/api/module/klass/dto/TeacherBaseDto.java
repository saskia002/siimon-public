package siimon.core.api.module.klass.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherBaseDto {

	@Min(0)
	@Max(Integer.MAX_VALUE/2)
	private Integer id;

	@Min(0)
	@Max(Integer.MAX_VALUE/2)
	private Integer courseTeacherId;

	@Size(max = 500)
	private String fullName;

	@Size(max = 1000)
	private String contact;

}
