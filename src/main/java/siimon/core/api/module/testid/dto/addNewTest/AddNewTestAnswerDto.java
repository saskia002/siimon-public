package siimon.core.api.module.testid.dto.addNewTest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddNewTestAnswerDto {

	private Boolean correct;

	private String answer;

}
