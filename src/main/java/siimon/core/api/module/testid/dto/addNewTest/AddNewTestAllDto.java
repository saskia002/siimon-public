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
public class AddNewTestAllDto {

	private AddNewTestTestDto test;

	private List<AddNewTestPartDto> parts;


}
