package siimon.core.api.module.testid.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import siimon.core.api.module.testid.dto.TestSubmitDto;
import siimon.core.api.module.testid.dto.saveUserResult.SaveUserResultDto;
import siimon.core.api.module.testid.service.ITestidUserService;
import siimon.core.api.module.testid.util.UrlPaths;
import siimon.core.api.shared.dto.RespDto;

@Log4j2
@RestController
@RequestMapping(UrlPaths.TESTID_MAPPING)
@RequiredArgsConstructor
@CrossOrigin
public class TestidUserController {

	private final ITestidUserService testidService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(
			value = "/{key}",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public RespDto addNewPersonTestSubmit(@RequestBody @Valid TestSubmitDto testSubmitDto, @PathVariable String key) {
		log.info("Adding person test submit: "+ testSubmitDto);
		return testidService.addNewPersonTestSubmit(testSubmitDto, key);
	}

	@RequestMapping(
			value = "/{key}/{step}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = {RequestMethod.GET, RequestMethod.HEAD}
	)
	public RespDto getTestByKeyAndStep(@PathVariable String key, @PathVariable Integer step) {
		log.info("Getting test by key: " + key + ", and step: ");
		return testidService.getTestByKeyAndStep(key, step);
	}

	@PostMapping(
			value = "/{key}/{step}",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public RespDto saveUserResult(
			@RequestBody @Valid SaveUserResultDto SaveUserResultDto, @PathVariable String key, @PathVariable Integer step
	) {
		log.info("Saving user result: " + SaveUserResultDto);
		return testidService.saveUserResult(SaveUserResultDto, key, step);
	}

}
