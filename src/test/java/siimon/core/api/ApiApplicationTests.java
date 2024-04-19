package siimon.core.api;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiApplicationTests {

//	@Test
//	void test() {
//		var encoder = new BCryptPasswordEncoder(16);
//		String result = encoder.encode("myPassword");
//		assertTrue(encoder.matches("myPassword", result));
//	}
//
//	@Test
//	void test2() {
//		var encoder2 = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
//		String result2 = encoder2.encode("myPassword");
//		assertTrue(encoder2.matches("myPassword", result2));
//	}
//
//	@Test
//	void generateKey() throws NoSuchAlgorithmException {
//		var keyGenerator = KeyGenerator.getInstance("AES");
//		keyGenerator.init(256);
//		var key = keyGenerator.generateKey();
//		System.out.println(Base64.getEncoder().encodeToString(key.getEncoded()));
//	}
//
//	@Test
//	void testKeyEncryptDecrypt() {
//		var key = "test";
//		try {
//			var encrypted = AESEncryptUtil.encrypt(key);
//			var decrypted = AESEncryptUtil.decrypt(encrypted);
//
//			var	encrypted2 = AESEncryptUtil.encrypt(key);
//			var decrypted2 = AESEncryptUtil.decrypt(encrypted2);
//
//			assertEquals(decrypted, decrypted2);
//
//			System.out.println(AESEncryptUtil.decrypt("WkRUT0MgscGjbJetGQV/mLUG9GMdD2Y1xdV3p5nx+u8="));
//
//			assertEquals(decrypted, key);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}


//	@Test
//	void test() {
//		String inputDateTime = "2021-01-07T22:00:00Z";
//		var instant = Instant.parse(inputDateTime);
//
//		var sourceZone = ZoneId.of("UTC"); // Source timezone
//		var targetZone = ZoneId.systemDefault(); // Target timezone
//
//		var zonedSourceDateTime = instant.atZone(ZoneId.of("Europe/Tallinn"));
//		var zonedTargetDateTime = zonedSourceDateTime.withZoneSameInstant(targetZone);
//
//		String formattedDateTime = zonedTargetDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
//		System.out.println(formattedDateTime);
//
//
//		var asd = LocalDate.parse("2021-01-07")
//				.atTime(LocalTime.of(0, 0))
//				.atZone(ZoneId.of("UTC"))
//				.toInstant();
//
//		System.out.println(asd);
//
//	}

}
