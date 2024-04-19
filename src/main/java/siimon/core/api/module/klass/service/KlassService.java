package siimon.core.api.module.klass.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import siimon.core.api.module.klass.dto.ClassReturnDto;
import siimon.core.api.module.klass.mapper.ClassMapper;
import siimon.core.api.module.klass.repository.ClassRepository;
import siimon.core.api.shared.dto.RespDto;
import siimon.core.api.shared.exception.BadReqException;
import siimon.core.api.shared.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class KlassService implements IKlassService {

	private final ClassRepository classRepository;


	@Transactional(readOnly = true)
	public ClassReturnDto getClassByKey(String key) {
		if (key == null || key.isEmpty()) {
			throw new BadReqException("Key cannot be null or empty", "keyNull");
		}
		var classEntity = classRepository.findByKey(key)
			.orElseThrow(() -> new NotFoundException("Class with key " + key + " not found", "classNotFound"));
		if (classEntity == null) {
			throw new NotFoundException("Class with key " + key + " not found", "classNotFound");
		}
		return ClassMapper.mapToDto(classEntity);
	}

	@Transactional(readOnly = true)
	public RespDto checkClassByKey(String key) {
		if (key == null || key.isEmpty()) {
			throw new BadReqException("Key cannot be null or empty", "keyNull");
		}

		var classEntity = classRepository.existsByKey(key);

		if (!classEntity) {
			throw new NotFoundException("Class with key " + key + " not found", "classNotFound");
		}

		return new RespDto("checkClassByKey", "Class with key " + key + " found","/v2/klass");
	}

}
