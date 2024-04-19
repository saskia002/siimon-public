package siimon.core.api.module.testid.service;

import siimon.core.api.module.testid.dto.TestSubmitDto;
import siimon.core.api.module.testid.dto.saveUserResult.SaveUserResultDto;
import siimon.core.api.shared.dto.RespDto;

public interface ITestidUserService {

	RespDto addNewPersonTestSubmit(TestSubmitDto testSubmitDto, String key);
	RespDto getTestByKeyAndStep(String key, Integer step);
	RespDto saveUserResult(SaveUserResultDto saveUserResultDto, String key, Integer step);

}
