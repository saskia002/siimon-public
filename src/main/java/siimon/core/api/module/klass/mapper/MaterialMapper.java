package siimon.core.api.module.klass.mapper;

import siimon.core.api.module.klass.dto.MaterialBaseDto;
import siimon.core.api.module.klass.dto.addMaterialDto;
import siimon.core.api.module.klass.model.ClassMaterialModel;
import siimon.core.api.module.klass.model.MaterialModel;
import siimon.core.api.shared.util.DateUtil;

import java.util.LinkedHashSet;
import java.util.Set;

public class MaterialMapper {

	public static MaterialModel mapToModel(addMaterialDto dto) {
		return MaterialModel.builder()
				.title(dto.getTitle())
				.description(dto.getDescription())
				.links(dto.getLinks())
				.build();
	}

	public static MaterialBaseDto mapToDto(MaterialModel model, ClassMaterialModel classMaterials) {
		return MaterialBaseDto.builder()
				.id(model.getId())
				.courseMaterialId(classMaterials.getId())
				.title(model.getTitle())
				.description(model.getDescription())
				.links(model.getLinks())
				.deadline(DateUtil.InstantToString(classMaterials.getDeadline()))
				.build();
	}

	public static LinkedHashSet<MaterialBaseDto> mapToDtoList(
			Set<MaterialModel> modelList, Set <ClassMaterialModel> classMaterials
	) {
		var list = new LinkedHashSet<MaterialBaseDto>();

		for (ClassMaterialModel classMaterialModel: classMaterials) {
			list.add(mapToDto(classMaterialModel.getMaterialModel(), classMaterialModel));
		}

		return list;
	}

}
