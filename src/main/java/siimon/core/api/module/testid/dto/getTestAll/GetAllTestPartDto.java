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
public class GetAllTestPartDto {

	private Integer id;

	private String description;

	private String maxTime;

	private Integer stepCount;

	private Integer step;

	private List<GetAllTestQuestionDto> questions;

}
