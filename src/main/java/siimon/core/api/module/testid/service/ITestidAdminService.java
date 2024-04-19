package siimon.core.api.module.testid.service;

import siimon.core.api.module.testid.dto.addNewTest.AddNewTestAllDto;
import siimon.core.api.shared.dto.RespDto;

public interface ITestidAdminService {

	RespDto addNewTestWithAllData(AddNewTestAllDto addNewTestAllDto);


	// TODO: add more methods
	//	RespDto addQuestion();
	//	RespDto addAnswer();
	//	RespDto addTestParts();
	//	RespDto addPart();
	//	RespDto addQuestionWithAnswers();


}
