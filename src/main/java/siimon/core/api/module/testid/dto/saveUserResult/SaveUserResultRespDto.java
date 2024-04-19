package siimon.core.api.module.testid.dto.saveUserResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserResultRespDto {

	private String langLevel;

	private int resultPercentage;

	private boolean nextPart;

//	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer nextPartNr;

}
