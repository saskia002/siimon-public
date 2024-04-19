package siimon.core.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@EnableJdbcHttpSession
@Configuration(proxyBeanMethods = false)
@EnableMethodSecurity(securedEnabled = true)
public class SessionConfig {

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	public SecurityFilterChain sessionFilterChain(HttpSecurity http) throws Exception {
		return http
				.sessionManagement((sess) -> {
					sess.init(http);
					sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
					sess.maximumSessions(1).maxSessionsPreventsLogin(true);
					sess.sessionConcurrency((con) -> con.maximumSessions(1));
					sess.sessionFixation().changeSessionId();
				})
				.logout((logout) -> {
					logout.logoutUrl("/v2/auth/logout");
					logout.deleteCookies("Siimon");
					logout.invalidateHttpSession(true);
				})
				.formLogin(AbstractHttpConfigurer::disable)
				.httpBasic((basic) -> {
					basic.addObjectPostProcessor(
							new ObjectPostProcessor<BasicAuthenticationFilter>() {
								@Override public <O extends BasicAuthenticationFilter> O postProcess(O filter) {
									filter.setSecurityContextRepository(new HttpSessionSecurityContextRepository());
									return filter;
								}
							}
					);
				})
				.build();
	}

	@Bean
	public CookieSerializer cookieSerializer() {
		var serializer = new DefaultCookieSerializer();
		serializer.setCookieName("Siimon");
//		serializer.setCookiePath("/");
//		serializer.setUseHttpOnlyCookie(true);
//		serializer.setSameSite("Strict");
//		serializer.setUseSecureCookie(true);
//		serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
		return serializer;
	}

}
