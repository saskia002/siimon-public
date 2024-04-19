package siimon.core.api.module.klass.mapper;

import siimon.core.api.module.klass.dto.TeacherBaseDto;
import siimon.core.api.module.klass.dto.TeacherDto;
import siimon.core.api.module.klass.model.ClassTeacherModel;
import siimon.core.api.module.klass.model.TeacherModel;

import java.util.LinkedHashSet;
import java.util.Set;

public class TeacherMapper {

	public static TeacherModel mapToModel(TeacherDto dto) {
		return TeacherModel.builder()
				.fullName(dto.getFullName())
				.contact(dto.getContact())
				.build();
	}

	public static TeacherBaseDto mapToDto(ClassTeacherModel model) {
		return TeacherBaseDto.builder()
				.id(model.getTeacherModel().getId())
				.courseTeacherId(model.getId())
				.fullName(model.getTeacherModel().getFullName())
				.contact(model.getTeacherModel().getContact())
				.build();
	}

	public static LinkedHashSet<TeacherBaseDto> mapToDtoList(Set<ClassTeacherModel> modelList) {
		var list = new LinkedHashSet<TeacherBaseDto>();
		for (ClassTeacherModel model : modelList) {
			list.add(mapToDto(model));
		}
		return list;
	}

}
