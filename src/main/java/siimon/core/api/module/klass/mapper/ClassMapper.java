package siimon.core.api.module.klass.mapper;

import siimon.core.api.module.klass.dto.ClassDto;
import siimon.core.api.module.klass.dto.ClassReturnDto;
import siimon.core.api.module.klass.model.ClassModel;
import siimon.core.api.shared.util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassMapper {

	public static ClassModel mapToModel(ClassDto classDto) {
		return ClassModel.builder()
				.meets(classDto.getMeets())
				.schedule(classDto.getSchedule())
				.location(classDto.getLocation())
				.key(classDto.getKey().toUpperCase())
				.title(classDto.getTitle())
				.description(classDto.getDescription())
				.begins(LocalDate.parse(classDto.getBegins()))
				.ends(LocalDate.parse(classDto.getEnds()))
				.build();
	}

	public static ClassReturnDto mapToDto(ClassModel classModel) {
		return ClassReturnDto.builder()
				.meets(classModel.getMeets())
				.schedule(classModel.getSchedule())
				.location(classModel.getLocation())
				.id(classModel.getId())
				.key(classModel.getKey().toUpperCase())
				.title(classModel.getTitle())
				.description(classModel.getDescription())
				.begins(DateUtil.localDateToString(classModel.getBegins()))
				.ends(DateUtil.localDateToString(classModel.getEnds()))
				.materials(MaterialMapper.mapToDtoList(classModel.getMaterials(), classModel.getClassMaterials()))
				.teachers(TeacherMapper.mapToDtoList(classModel.getClassTeacher()))
				.build();
	}

	public static List<ClassReturnDto> maptoDtoList(List<ClassModel> classModelList) {
		var list = new ArrayList<ClassReturnDto>();
		for (ClassModel classModel : classModelList) {
			list.add(mapToDto(classModel));
		}
		return list;
	}

}
