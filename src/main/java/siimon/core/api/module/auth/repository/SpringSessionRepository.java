package siimon.core.api.module.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.auth.model.SpringSession;

import java.time.LocalDateTime;

@Repository
public interface SpringSessionRepository extends JpaRepository<SpringSession, Long> {

	@Modifying
	@Query("DELETE FROM SpringSession s WHERE s.expiryTime < :expiryTime")
	void deleteExpiredSessions(@Param("expiryTime") LocalDateTime expiryTime);

}
