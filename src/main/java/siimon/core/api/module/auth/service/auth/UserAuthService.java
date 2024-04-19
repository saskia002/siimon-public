package siimon.core.api.module.auth.service.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import siimon.core.api.module.auth.repository.UserRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {

	private final UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Logging in user: " + username.toLowerCase());
		var userModel = repository.findByUsername(username.toLowerCase())
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return new UserAuthDetails(userModel);
	}
	
	public UserRepository getUserRepository() {
		return repository;
	}

}
