package siimon.core.api.module.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import siimon.core.api.module.auth.dto.UserDto;
import siimon.core.api.module.auth.dto.UserNameDto;
import siimon.core.api.module.auth.dto.UserPasswordDto;
import siimon.core.api.module.auth.model.UserModel;
import siimon.core.api.module.auth.service.IUserService;
import siimon.core.api.shared.dto.RespDto;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/auth/user")
//@PreAuthorize("hasRole('ADMIN')")
public class UserController {

	private final IUserService service;

	// * USERS
	@PostMapping(
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseStatus(HttpStatus.CREATED)
	public RespDto registerNewUser(@RequestBody @Valid UserDto userDto) {
		log.info("Adding new user");
		return service.registerNewUser(userDto);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserModel> getAllRegisteredUsers() {
		log.info("Getting all registered users");
		return service.getAllRegisteredUsers();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserModel getRegisteredUser(@PathVariable("id") Integer id) {
		log.info("Getting registered user with id: " + id);
		return service.getRegisteredUser(id);
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RespDto updateUser(@PathVariable("id") Integer id, @RequestBody @Valid UserDto userDto) {
		log.info("Updating user with id: " + id);
		return service.updateUser(id, userDto, 0);
	}

	@PutMapping(value = "/name/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RespDto updateUserName(@PathVariable("id") Integer id, @RequestBody @Valid UserNameDto dto) {
		log.info("Updating user with id: " + id);
		return service.updateUser(id, UserDto.builder().username(dto).build(), 1);
	}

	@PutMapping(value = "/password/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RespDto updateUserPassword(@PathVariable("id") Integer id, @RequestBody @Valid UserPasswordDto dto) {
		log.info("Updating user with id: " + id);
		return service.updateUser(id, UserDto.builder().password(dto).build(), 2);
	}

	@DeleteMapping(
			value = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public RespDto deleteUser(@PathVariable("id") Integer id) {
		log.info("Deleting user with id: " + id);
		return service.deleteUser(id);
	}

}
