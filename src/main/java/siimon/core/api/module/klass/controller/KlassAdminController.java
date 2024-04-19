package siimon.core.api.module.klass.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import siimon.core.api.module.klass.dto.*;
import siimon.core.api.module.klass.service.IKlassAdminService;
import siimon.core.api.module.klass.util.UrlPaths;
import siimon.core.api.shared.dto.RespDto;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(UrlPaths.KLASS_MAPPING)
public class KlassAdminController {

	private final IKlassAdminService klassService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public RespDto addNewClass(@RequestBody @Valid ClassDto classDto) {
		log.info("Adding new class: " + classDto.toString());
		return klassService.addNewClass(classDto);
	}

	@PostMapping(
			value = "/{key}/material",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseStatus(HttpStatus.CREATED)
	public RespDto addNewClassMaterial(@RequestBody @Valid addMaterialDto materialDto, @PathVariable String key) {
		log.info("Adding new class material: " + materialDto.toString() + ", to class with key: " + key);
		return klassService.addNewClassMaterial(materialDto, key);
	}

	@PostMapping(
			value = "/{id}/teacher",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseStatus(HttpStatus.CREATED)
	public RespDto addNewClassTeacher(@RequestBody @Valid NewClassTeacherDto dto, @PathVariable Integer id) {
		log.info("Adding new class material: " + dto.toString() + ", to class with id: " + id);
		return klassService.addNewClassTeacher(dto, id);
	}

	@PostMapping(
			value = "/teacher",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseStatus(HttpStatus.CREATED)
	public RespDto addNewTeacher(@RequestBody @Valid TeacherDto teacherDto) {
		log.info("Adding new class teacher: " + teacherDto.toString());
		return klassService.addNewTeacher(teacherDto);
	}

	@PostMapping(
			value = "/material",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseStatus(HttpStatus.CREATED)
	public RespDto addNewMaterial(@RequestBody @Valid addMaterialDto materialDto) {
		log.info("Adding new ćlass material: " + materialDto.toString());
		return klassService.addNewMaterial(materialDto);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClassReturnDto> getAllClasses() {
		log.info("Getting all class");
		return klassService.getAllClasses();
	}

}
