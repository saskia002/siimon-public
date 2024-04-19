package siimon.core.api.shared.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@UtilityClass
public class PasswordEncoderUtil {

	private static final Pbkdf2PasswordEncoder encoder = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();

	public static String encode(String password) {
		return encoder.encode(password);
	}

	public static boolean matches(String password, String encodedPassword) {
		return encoder.matches(password, encodedPassword);
	}
	
}
