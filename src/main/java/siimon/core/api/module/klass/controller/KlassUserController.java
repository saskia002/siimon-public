package siimon.core.api.module.klass.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import siimon.core.api.module.klass.dto.ClassReturnDto;
import siimon.core.api.module.klass.service.IKlassService;
import siimon.core.api.module.klass.util.UrlPaths;

@Log4j2
@RestController
@RequestMapping(UrlPaths.KLASS_MAPPING)
@RequiredArgsConstructor
public class KlassUserController {

	private final IKlassService klassService;

	@RequestMapping(
			value = "/{key}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = {RequestMethod.GET, RequestMethod.HEAD}
	)
	public ClassReturnDto getClassByKey(@PathVariable String key) {
		log.info("Getting class with key: " + key.toUpperCase());
		return klassService.getClassByKey(key.toUpperCase());
	}

}
