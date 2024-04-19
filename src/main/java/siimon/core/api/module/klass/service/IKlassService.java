package siimon.core.api.module.klass.service;

import siimon.core.api.module.klass.dto.ClassReturnDto;
import siimon.core.api.shared.dto.RespDto;

public interface IKlassService {

	ClassReturnDto getClassByKey(String key);

	RespDto checkClassByKey(String key);

}
