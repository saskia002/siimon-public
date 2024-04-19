package siimon.core.api.module.avaleht.mapper;

import lombok.extern.log4j.Log4j2;
import siimon.core.api.module.avaleht.dto.PersonDto;
import siimon.core.api.module.avaleht.model.PersonModel;
import siimon.core.api.shared.util.AESEncryptUtil;

import java.util.Objects;

@Log4j2
public class PersonMapper {

	public static PersonModel mapToModel(PersonDto dto) {
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

	public static PersonDto mapToDto(PersonModel model) {
		try {
			return PersonDto.builder()
					.id(model.getId())
					.fullName(AESEncryptUtil.decrypt(model.getFullName()))
					.mail(AESEncryptUtil.decrypt(model.getMail()))
					.phone(AESEncryptUtil.decrypt(model.getPhone()))
					.idCode(AESEncryptUtil.decrypt(model.getIdCode()))
					.build();
		} catch (Exception e) {
			log.error("Error while decrypting person data: " +
							  (! Objects.equals(e.getMessage(), "") ? e.getMessage() : "null"));;
			return null;
		}
	}

}
