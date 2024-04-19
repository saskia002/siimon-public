package siimon.core.api.module.avaleht.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import siimon.core.api.module.avaleht.dto.CourseDto;
import siimon.core.api.module.avaleht.service.ICourseService;
import siimon.core.api.module.avaleht.util.UrlPaths;
import siimon.core.api.shared.dto.RespDto;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(UrlPaths.AVALEHT_COURSE_MAPPING)
@RequiredArgsConstructor
public class CourseController {

	private final ICourseService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CourseDto> getAllCourses() {
		log.info("Getting all courses");
		return service.getAllCourses();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public RespDto addCourse(@RequestBody @Valid CourseDto courseDto) {
		log.info("Adding new course: " + courseDto.toString());
		return service.addCourse(courseDto);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RespDto deleteCourse(@PathVariable Integer id) {
		log.info("Deleting course with id: " + id);
		return service.deleteCourse(id);
	}

}
