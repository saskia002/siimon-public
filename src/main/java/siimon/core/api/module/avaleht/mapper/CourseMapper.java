package siimon.core.api.module.avaleht.mapper;

import siimon.core.api.module.avaleht.dto.CourseDto;
import siimon.core.api.module.avaleht.model.CourseModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseMapper {

	public static CourseModel mapToModel(CourseDto dto) {
		return CourseModel.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.titleRegistration(dto.getTitleRegistration())
				.description(dto.getDescription())
				.links(dto.getLinks().toString())
				.date(dto.getDate())
				.build();
	}

	public static CourseDto mapToDto(CourseModel model) {
		return CourseDto.builder()
				.id(model.getId())
				.title(model.getTitle())
				.titleRegistration(model.getTitleRegistration())
				.links(model.getLinks())
				.description(model.getDescription())
				.date(model.getDate())
				.build();
	}

	public static List<CourseDto> mapToDtos(List<CourseModel> model) {
		var list = new ArrayList<CourseDto>();
		for (CourseModel courseModel : model) {
			list.add(mapToDto(courseModel));
		}
		return list;
	}

}
