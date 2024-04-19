package siimon.core.api.shared.util;

import lombok.experimental.UtilityClass;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

@UtilityClass
public class AESKeyGenUtil {
	private static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
		var keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(n);
		var key = keyGenerator.generateKey();
		return key;
	}

	private static SecretKey getKeyFromPassword(String password, String salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		var spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
		var secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
		return secret;
	}

	private static IvParameterSpec generateSalt() {
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		return new IvParameterSpec(iv);
	}

}
