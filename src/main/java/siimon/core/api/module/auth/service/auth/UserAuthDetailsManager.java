package siimon.core.api.module.auth.service.auth;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import siimon.core.api.module.auth.model.AuthorityModel;
import siimon.core.api.module.auth.model.UserModel;
import siimon.core.api.module.auth.model.UsersAuthorityModel;
import siimon.core.api.module.auth.repository.AuthorityRepository;
import siimon.core.api.module.auth.repository.UserRepository;
import siimon.core.api.module.auth.repository.UsersAuthorityRepository;
import siimon.core.api.shared.util.PasswordEncoderUtil;
import siimon.core.api.shared.exception.NotFoundException;

import java.util.*;

@Log4j2
@Component
public class UserAuthDetailsManager implements UserDetailsManager {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private UsersAuthorityRepository usersAuthorityRepository;

	@Override
	@Transactional
	public void createUser(UserDetails user) {
		Set<AuthorityModel> authorities = new HashSet<>();
		try {
			if (userExists(user.getUsername())) {
				log.error("Error whilst creating new user: Username already exists");
				throw new UsernameNotFoundException("Username already exists");
			}
			user.getAuthorities().forEach(role -> {
				var authority = authorityRepository.findByAuthority(role.getAuthority());
				if (authority == null) {
					authority = authorityRepository.save(AuthorityModel.createNew(role.getAuthority()));
				}
				authorities.add(authority);
			});

			var userModel = userRepository.save(
				UserModel.createNew(
					user.getUsername().toLowerCase(),
					PasswordEncoderUtil.encode(user.getPassword())
				)
			);

			for (var authority : authorities) {
				usersAuthorityRepository.save(
						UsersAuthorityModel
								.builder()
								.users(userModel)
								.authorities(authority)
								.build()
				);
			}


			log.info("New user created: " + user.getUsername());
		} catch (Exception e) {
			log.error("Error while creating new user: " +
							  (! Objects.equals(e.getMessage(), "") ? e.getMessage() : "Data fetch error, null"));
		}
	}

	@Override
	@Transactional
	public void updateUser(UserDetails user) {
		try {
			var userModel = userRepository.findByUsername(user.getUsername())
					.orElseThrow(() -> new NotFoundException("User not found"));

			Set<AuthorityModel> authorities = new HashSet<>();
			for (GrantedAuthority auth : user.getAuthorities()) {
				var authority = authorityRepository.findByAuthority(auth.getAuthority());
				if (authority == null) {
					authority = authorityRepository.save(AuthorityModel.createNew(auth.getAuthority()));
				}
				authorities.add(authority);
			}
			userModel.setDeleted(user.isEnabled());
			userModel.setAuthorities(authorities);
			userModel.setUsername(user.getUsername().toLowerCase());
			userModel.setPassword(PasswordEncoderUtil.encode(user.getPassword()));
			userRepository.save(userModel);
			log.info("User updated: " + user.getUsername());
		} catch (Exception e) {
			log.error("Error whilst updating user: " +
							  (!Objects.equals(e.getMessage(), "") ? e.getMessage() : "Data fetch error, null"));
		}
	}

	@Override
	@Transactional
	public void deleteUser(String username) {
		try {
			var user = userRepository.findByUsername(username.toLowerCase())
					.orElseThrow(() -> new NotFoundException("User not found"));
			userRepository.delete(user);
			log.info("User deleted: " + username);
		} catch (Exception e) {
			log.error("Error whilst deleting user: " +
							  (!Objects.equals(e.getMessage(), "") ? e.getMessage() : "Data fetch error, null"));
		}
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		log.error("Error whilst changing password: Method not implemented");
	}

	@Override
	@Transactional(readOnly = true)
	public boolean userExists(String username) {
		return userRepository.findByUsername(username.toLowerCase()).isPresent();
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = userRepository.findByUsername(username.toLowerCase());
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		for (AuthorityModel auth : user.get().getAuthorities()) {
			var roleStr = auth.getAuthority().toUpperCase().startsWith("ROLE_") ?
					auth.getAuthority().toUpperCase() : "ROLE_" + auth.getAuthority().toUpperCase();
			authorities.add(new SimpleGrantedAuthority(roleStr));
		}

		return new User(
				user.get().getUsername().toLowerCase(),
				user.get().getPassword(),
				authorities
		);
	}

}
