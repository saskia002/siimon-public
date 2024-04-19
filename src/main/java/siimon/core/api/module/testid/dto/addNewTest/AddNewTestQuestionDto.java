package siimon.core.api.module.testid.dto.addNewTest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddNewTestQuestionDto {

	private String maxTime;

	private String question;

	private double points;

	private List<AddNewTestAnswerDto> answers;

}
