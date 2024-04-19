package siimon.core.api.module.klass.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import siimon.core.api.module.klass.dto.*;
import siimon.core.api.module.klass.mapper.ClassMapper;
import siimon.core.api.module.klass.mapper.MaterialMapper;
import siimon.core.api.module.klass.mapper.TeacherMapper;
import siimon.core.api.module.klass.model.ClassMaterialModel;
import siimon.core.api.module.klass.model.ClassTeacherModel;
import siimon.core.api.module.klass.repository.*;
import siimon.core.api.shared.dto.RespDto;
import siimon.core.api.shared.exception.BadReqException;
import siimon.core.api.shared.exception.NotFoundException;
import siimon.core.api.shared.exception.UnprocessableException;
import siimon.core.api.shared.util.DateUtil;

import java.util.List;


@Service
@RequiredArgsConstructor
public class KlassAdminService implements IKlassAdminService {

	private final TeacherRepository teacherRepository;
	private final MaterialRepository materialRepository;
	private final ClassRepository classRepository;
	private final ClassTeacherRepository classTeacherRepository;
	private final ClassMaterialRepository classMaterialRepository;


	@Transactional
	public RespDto addNewClass(ClassDto classDto) {
		if (classRepository.findByKey(classDto.getKey()).isPresent()) {
			throw new BadReqException("Class with key " + classDto.getKey() + " already exists");
		}

		var newClass = classRepository.save(ClassMapper.mapToModel(classDto));

		if (classDto.getTeacherId() != null) {
			var classTeacher = teacherRepository.findById(classDto.getTeacherId());
			if (classTeacher.isEmpty()) {
				throw new BadReqException("Teacher with id " + classDto.getTeacherId() + " not found");
			}

			classTeacherRepository.save(
					ClassTeacherModel.builder()
							.teacherModel(classTeacher.get())
							.classModel(newClass)
							.build()
			);
		}

		return new RespDto("addNewClass", "Added new class successfully", "/v2/klass");
	}

	@Transactional
	public RespDto addNewTeacher(TeacherDto teacherDto) {
		teacherRepository.save(TeacherMapper.mapToModel(teacherDto));
		return new RespDto("addNewTeacher", "Added new material successfully", "/v2/klass/teacher");
	}

	@Transactional
	public RespDto addNewMaterial(addMaterialDto materialDto) {
		materialRepository.save(MaterialMapper.mapToModel(materialDto));
		return new RespDto("addNewMaterial", "Added new material successfully","/v2/klass/material");
	}

	@Transactional
	public RespDto addNewClassMaterial(addMaterialDto materialDto, String key) {
		var newMaterial = materialRepository.save(MaterialMapper.mapToModel(materialDto));
		var newClass = classRepository.findByKey(key)
				.orElseThrow(() -> new NotFoundException("Class not found", "classNotFound"));

		classMaterialRepository.save(
				ClassMaterialModel.builder()
				.deadline(
						DateUtil.stringToInstant(
								materialDto.getDeadline()
						)
				)
				.classModel(newClass)
				.materialModel(newMaterial)
				.build()
		);

		return new RespDto("addNewClassMaterial", "Added new class material successfully",
						   "/v2/klass/" + key + "/material");
	}
	@Transactional
	public RespDto addNewClassTeacher(NewClassTeacherDto dto, Integer id) {
		if (id == null || dto.getId() == null) {
			throw new BadReqException("Class id and teacher id must be provided", "idNull");
		}

		var	teacher = teacherRepository.findById(dto.getId())
					.orElseThrow(() -> new NotFoundException("Teacher not found", "teacherNotFound"));

		var _class = classRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Class not found", "classNotFound"));

		var classTeacher = classTeacherRepository.findByClassModelAndTeacherModel(_class, teacher);
		if (classTeacher != null) {
			throw new UnprocessableException("Teacher already assigned to this class", "teacherAlreadyAssigned");
		}

		classTeacherRepository.save(
			ClassTeacherModel.builder()
				.teacherModel(teacher)
				.classModel(_class)
				.build()
		);

		return new RespDto("addNewClassTeacher", "Added new class teacher successfully",
						   "/v2/klass" + id + "/teacher");
	}

	@Transactional(readOnly = true)
	public List<ClassReturnDto> getAllClasses() {
		return ClassMapper.maptoDtoList(classRepository.findAll());
	}

}
