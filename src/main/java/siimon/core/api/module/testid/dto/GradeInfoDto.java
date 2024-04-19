package siimon.core.api.module.testid.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeInfoDto {

	private Integer langLevelId;

	private String langLevel;

	private Boolean nextPart;

}
