package siimon.core.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import siimon.core.api.module.auth.service.auth.UserAuthDetailsManager;
import siimon.core.api.module.auth.service.auth.UserAuthService;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig  {

	private final UserAuthService UserAuthService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.disable())
				.authorizeHttpRequests(req -> {
					// Klass routes
					req.requestMatchers("/v2/klass/**").permitAll();

					// Testid routes
					req.requestMatchers("/v2/testid/**").permitAll();

					// User auth routes
					req.requestMatchers("/v2/auth/login").permitAll();
					req.requestMatchers("/v2/auth/logout").permitAll();
					req.requestMatchers("/v2/auth/session").permitAll();
					req.requestMatchers("/v2/auth/data").permitAll();

					//req.requestMatchers("/v2/**").permitAll();

					req.anyRequest().denyAll();
				})
				.headers(withDefaults())
				.headers((headers) -> {
					 headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
					 headers.contentSecurityPolicy(csp -> {
						 csp.policyDirectives(
							 "default-src 'self' https://www.gstatic.com https://www.google.com;" +
							 "script-src 'self' https://www.gstatic.com https://www.google.com; " +
							 "connect-src 'self';" +
							 "img-src 'self';" +
							 "frame-ancestors 'self' https://www.gstatic.com https://www.google.com;" +
							 "style-src 'self' https://fonts.googleapis.com https://fonts.gstatic.com 'unsafe-inline';" +
							 "form-action 'self';" +
							 "font-src 'self';" +
							 "object-src 'self';" +
							 "block-all-mixed-content;" +
							 "Access-Control-Allow-Origin: *;"
						 );
					 });
			 	})
				.authenticationProvider(authenticationProvider())
				.build()
		;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
	}

	@Bean
	public UserDetailsManager users(DataSource dataSource) {
		UserDetails user = User.builder()
				.username("hackerman")
				.password("l33th4x0r")
				.roles("ADMIN")
				.build();
		var users = customAuthProvider();
		users.createUser(user);
		return users;
	}

	@Bean
	public UserAuthDetailsManager customAuthProvider() {
		return new UserAuthDetailsManager();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		var authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(UserAuthService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

}
