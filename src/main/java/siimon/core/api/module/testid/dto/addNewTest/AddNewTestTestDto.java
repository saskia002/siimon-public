package siimon.core.api.module.testid.dto.addNewTest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddNewTestTestDto {

	private String key;

	private String title;

	private String description;

}
