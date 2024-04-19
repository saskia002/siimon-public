package siimon.core.api.module.avaleht.service;

import siimon.core.api.module.avaleht.dto.CourseDto;
import siimon.core.api.module.avaleht.dto.CourseRegDto;
import siimon.core.api.module.avaleht.dto.courseReg.AddCourseRegDto;
import siimon.core.api.shared.dto.RespDto;

import java.util.List;

public interface ICourseService {

	List<CourseDto> getAllCourses();

	RespDto addCourse(CourseDto courseDto);

	RespDto deleteCourse(Integer id);

	List<CourseRegDto> getAllCourseRegistrations();

	RespDto registerCourse(AddCourseRegDto addCourseRegDto);

}
