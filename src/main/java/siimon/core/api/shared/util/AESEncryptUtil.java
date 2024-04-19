package siimon.core.api.shared.util;

import lombok.experimental.UtilityClass;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@UtilityClass
public class AESEncryptUtil {

	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final String KEY = "????????????????????";

	public static String encrypt(String input) throws
			NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException,
			InvalidKeySpecException, InvalidKeyException, InvalidAlgorithmParameterException {
		var cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, generateKey(), generateSalt());
		byte[] cipherText = cipher.doFinal(input.getBytes());
		return Base64.getEncoder().encodeToString(cipherText);
	}

	public static String decrypt(String cipherText) throws
			NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException,
			InvalidKeySpecException, InvalidKeyException, InvalidAlgorithmParameterException {
		var cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, generateKey(), generateSalt());
		byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(plainText);
	}

	private static Key generateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
		return new SecretKeySpec(Base64.getDecoder().decode(KEY), ALGORITHM.split("/")[0]);
	}

	private static IvParameterSpec generateSalt() {
		byte[] iv = new byte[16];
		return new IvParameterSpec(iv);
	}

}
