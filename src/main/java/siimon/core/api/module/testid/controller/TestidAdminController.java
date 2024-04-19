package siimon.core.api.module.testid.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import siimon.core.api.module.testid.dto.addNewTest.AddNewTestAllDto;
import siimon.core.api.module.testid.service.ITestidAdminService;
import siimon.core.api.module.testid.util.UrlPaths;
import siimon.core.api.shared.dto.RespDto;

@Log4j2
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(UrlPaths.TESTID_MAPPING)
@RequiredArgsConstructor
public class TestidAdminController {

	private final ITestidAdminService testidService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(
			value = "/test/all",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public RespDto addNewPersonTestSubmit(@RequestBody @Valid AddNewTestAllDto addNewTestAllDto) {
		log.info("Adding test with all of the data: "+ addNewTestAllDto);
		return testidService.addNewTestWithAllData(addNewTestAllDto);
	}

}
