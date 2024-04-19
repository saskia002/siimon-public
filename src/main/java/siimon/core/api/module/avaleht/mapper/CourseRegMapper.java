package siimon.core.api.module.avaleht.mapper;

import siimon.core.api.module.avaleht.dto.CourseRegDto;
import siimon.core.api.module.avaleht.model.CourseRegModel;

import java.util.ArrayList;
import java.util.List;

public class CourseRegMapper {

	public static CourseRegModel mapToModel(CourseRegDto dto) {
		return CourseRegModel.builder()
				.id(dto.getId())
				.course(CourseMapper.mapToModel(dto.getCourse()))
				.person(PersonMapper.mapToModel(dto.getPerson()))
				.build();
	}

	public static CourseRegDto mapToDto(CourseRegModel model) {
		return CourseRegDto.builder()
				.id(model.getId())
				.created(model.getCreated())
				.course(CourseMapper.mapToDto(model.getCourse()))
				.person(PersonMapper.mapToDto(model.getPerson()))
				.build();
	}

	public static List<CourseRegDto> mapToDtos(List<CourseRegModel> model) {
		var list = new ArrayList<CourseRegDto>();
		for (CourseRegModel courseRegModel : model) {
			list.add(mapToDto(courseRegModel));
		}
		return list;
	}

}
