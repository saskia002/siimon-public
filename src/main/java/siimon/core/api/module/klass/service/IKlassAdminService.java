package siimon.core.api.module.klass.service;

import siimon.core.api.module.klass.dto.*;
import siimon.core.api.shared.dto.RespDto;

import java.util.List;


public interface IKlassAdminService {

	RespDto addNewClass(ClassDto classDto);

	RespDto addNewTeacher(TeacherDto teacherDto);

	RespDto addNewMaterial(addMaterialDto materialDto);

	List<ClassReturnDto> getAllClasses();

	RespDto addNewClassMaterial(addMaterialDto classMaterialDto, String key);

	RespDto addNewClassTeacher(NewClassTeacherDto dto, Integer id);

}
