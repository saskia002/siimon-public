package siimon.core.api.module.testid.dto.saveUserResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserResultDto {

	private Integer userId;

	private Integer testId;

	private Integer partNr;

	private Map<String, Integer> answers;

}
