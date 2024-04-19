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
public class AddNewTestPartDto {

	private String description;

	private String maxTime;

	private Integer step;

	private List<AddNewTestQuestionDto> questions;

}
