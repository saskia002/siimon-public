package siimon.core.api.module.testid.dto.getTestAll;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllTestQuestionDto {

	private Integer id;

	private String question;

	private double points;

	private List<GetAllTestAnswerDto> answers;

}
