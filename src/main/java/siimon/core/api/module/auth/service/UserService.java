package siimon.core.api.module.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import siimon.core.api.module.auth.dto.UserDto;
import siimon.core.api.module.auth.model.AuthorityModel;
import siimon.core.api.module.auth.model.UserModel;
import siimon.core.api.module.auth.model.UsersAuthorityModel;
import siimon.core.api.module.auth.repository.AuthorityRepository;
import siimon.core.api.module.auth.repository.UserRepository;
import siimon.core.api.module.auth.repository.UsersAuthorityRepository;
import siimon.core.api.shared.util.PasswordEncoderUtil;
import siimon.core.api.shared.dto.RespDto;
import siimon.core.api.shared.exception.NotFoundException;
import siimon.core.api.shared.exception.UnprocessableException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;
	private final UsersAuthorityRepository usersAuthorityRepository;

	private boolean usernameExists(String username) {
		return userRepository.findByUsername(username).isPresent();
	}
	private boolean userExists(Integer id) {
		return userRepository.findById(id).isPresent();
	}


	public RespDto updateUser(Integer id, UserDto userDto, int type) {
		var user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found", "userNotFound"));

		if (type == 0 || type == 1) {
			if (usernameExists(userDto.getUsername().getUsername().toLowerCase())){
				throw new UnprocessableException("Old and new username cannot be the same", "usernameUnchanged");
			}
			user.setUsername(userDto.getUsername().getUsername().toLowerCase());
		}
		if (type == 0 || type == 2) {
			user.setPassword(PasswordEncoderUtil.encode(userDto.getPassword().getPassword()));
		}
		userRepository.save(user);

		return new RespDto("deleteUser", "User updated successfully", "/v2/auth/user");
	}

	public RespDto deleteUser(Integer id) {
		var user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found"));
		userRepository.delete(user);
		return new RespDto("deleteUser", "User deleted successfully", "/v2/auth/user");
	}

	public List<UserModel> getAllRegisteredUsers() {
		List<UserModel> userRegs;
		userRegs = userRepository.findAll();
		if (userRegs.isEmpty()) { throw new NotFoundException("User(s) not found", "userNotFound"); }
		return userRegs;
	}

	public UserModel getRegisteredUser(Integer id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found", "userNotFound"));
	}

	@Transactional
	public RespDto registerNewUser(UserDto userDto) {
		Set<AuthorityModel> authorities = new HashSet<>();

		if (usernameExists(userDto.getUsername().getUsername())) {
			throw new UnprocessableException("Username already exists", "userAlreadyExists");
		}

		for (var role :userDto.getAuthorities()) {
			var roleStr = role.getAuthority().toUpperCase().startsWith("ROLE_") ?
					role.getAuthority().toUpperCase() : "ROLE_" + role.getAuthority().toUpperCase();
			var authority = authorityRepository.findByAuthority(roleStr);
			if (authority == null) {
				authority = authorityRepository.save(AuthorityModel.createNew(roleStr));
			}
			authorities.add(authority);
		}

		var user = userRepository.save(
				UserModel.createNew(
						userDto.getUsername().getUsername().toLowerCase(),
						PasswordEncoderUtil.encode(userDto.getPassword().getPassword())
				)
		);

		for (var authority : authorities) {
			usersAuthorityRepository.save(
					UsersAuthorityModel
							.builder()
							.users(user)
							.authorities(authority)
							.build()
			);
		}

		return new RespDto("registerNewUser", "User registered successfully", "/v2/auth/user");
	}

}
