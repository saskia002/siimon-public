package siimon.core.api.module.auth.service.session;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@Log4j2
public class SessionCleanupScheduler {

//	@Autowired
//	private SpringSessionService springSessionService;

//	@Scheduled(fixedDelay = 60000) // Run every minute
//	public void cleanupExpiredSessions() {
//		log.info("Running session cleanup scheduler");
//		LocalDateTime currentTime = LocalDateTime.now();
//		springSessionService.deleteExpiredSessions(currentTime);
//	}
}
