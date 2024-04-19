package siimon.core.api.module.avaleht.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import siimon.core.api.module.avaleht.dto.CourseRegDto;
import siimon.core.api.module.avaleht.dto.courseReg.AddCourseRegDto;
import siimon.core.api.module.avaleht.service.ICourseService;
import siimon.core.api.module.avaleht.util.UrlPaths;
import siimon.core.api.shared.dto.RespDto;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(UrlPaths.AVALEHT_COURSE_REGISTRATIONS_MAPPING)
@RequiredArgsConstructor
public class CourseRegController {

	private final ICourseService service;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CourseRegDto> getAllCourseRegistrations() {
		log.info("Getting all course registrations");
		return service.getAllCourseRegistrations();
	}

	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseStatus(HttpStatus.CREATED)
	public RespDto registerCourse(@RequestBody @Valid AddCourseRegDto addCourseRegDto) {
		log.info("Registering course");
		return service.registerCourse(addCourseRegDto);
	}

}
