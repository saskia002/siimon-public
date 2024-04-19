package siimon.core.api.module.avaleht.mapper;

import lombok.extern.log4j.Log4j2;
import siimon.core.api.module.avaleht.dto.courseReg.AddCourseRegDto;
import siimon.core.api.module.avaleht.dto.courseReg.AddCourseRegPersonDto;
import siimon.core.api.module.avaleht.model.CourseRegModel;
import siimon.core.api.module.avaleht.model.PersonModel;
import siimon.core.api.shared.util.AESEncryptUtil;

import java.util.Objects;

// * Course reg post request mapper
@Log4j2
public class AddCourseRegMapper {

	public static PersonModel mapToPersonModel(AddCourseRegPersonDto dto) {
		try {
			return PersonModel.builder()
					.fullName(AESEncryptUtil.encrypt(dto.getFullName()))
					.mail(AESEncryptUtil.encrypt(dto.getMail()))
					.phone(AESEncryptUtil.encrypt(dto.getPhone()))
					.idCode(AESEncryptUtil.encrypt(dto.getIdCode()))
					.build();
		} catch (Exception e) {
			log.error("Error while encrypting person data: " +
							  (! Objects.equals(e.getMessage(), "") ? e.getMessage() : "null"));
			return null;
		}
	}

	public static CourseRegModel mapToRegModel(AddCourseRegDto dto, Integer personId) {
		return CourseRegModel.builder()
				.courseId(dto.getCourseId())
				.personId(personId)
				.build();
	}

}
