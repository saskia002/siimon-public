package siimon.core.api.module.testid.dto.getTestAll;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllTestTestDto {

	private String key;

	private String title;

	private String description;

}
