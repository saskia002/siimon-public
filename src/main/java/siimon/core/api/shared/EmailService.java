package siimon.core.api.shared;

// https://mailtrap.io/blog/spring-send-email/

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender mailSender;


	public void sendEmail(String to, String subject, String body) {
		log.info("Sending email: " + to + " " + subject + " " + body);
		var executor = Executors.newSingleThreadExecutor();
		Future<?> future = executor.submit(() -> {
			var message = new SimpleMailMessage();
			message.setFrom("???????@????????.ee");
			message.setTo(to);
			message.setSubject(subject);
			message.setText(body);
			mailSender.send(message);
		});
		executor.shutdown();
	}

	public void sendHtmlEmail(String to, String subject, String body) throws RuntimeException {
		log.info("Sending email: " + to + " " + subject + " " + body);
		var executor = Executors.newSingleThreadExecutor();
		Future<?> future = executor.submit(() -> {
			var message = mailSender.createMimeMessage();
			try {
				message.setFrom(new InternetAddress("???????@????????.ee"));
				message.setRecipients(MimeMessage.RecipientType.TO, to);
				message.setSubject(subject);
				message.setContent(body, "text/html; charset=utf-8");
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			mailSender.send(message);
		});
		executor.shutdown();
	}

}
