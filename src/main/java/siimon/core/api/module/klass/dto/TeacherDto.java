package siimon.core.api.module.klass.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TeacherDto extends TeacherBaseDto {

	// * ClassTeacher
	@Min(0)
	@Max(Integer.MAX_VALUE/2)
	private Integer classId;

	@Min(0)
	@Max(Integer.MAX_VALUE/2)
	private Integer teacherId;

}
