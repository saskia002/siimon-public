package siimon.core.api.module.auth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spring_session", schema = "public",
		indexes = {@Index(name = "spring_session_ix3", columnList = "principal_name"),
				@Index(name = "spring_session_ix2", columnList = "expiry_time"),
				@Index(name = "spring_session_ix1", columnList = "session_id", unique = true)}
)
public class SpringSession implements Serializable {

	@Id
	@Size(max = 36)
	@Column(name = "primary_id", nullable = false, length = 36)
	private String primaryId;

	@Size(max = 36)
	@NotNull
	@Column(name = "session_id", nullable = false, length = 36)
	private String sessionId;

	@NotNull
	@Column(name = "creation_time", nullable = false)
	private Long creationTime;

	@NotNull
	@Column(name = "last_access_time", nullable = false)
	private Long lastAccessTime;

	@NotNull
	@Column(name = "max_inactive_interval", nullable = false)
	private Integer maxInactiveInterval;

	@NotNull
	@Column(name = "expiry_time", nullable = false)
	private Long expiryTime;

	@Size(max = 100)
	@Column(name = "principal_name", length = 100)
	private String principalName;

	@OneToMany(mappedBy = "sessionPrimary")
	private Set<SpringSessionAttribute> springSessionAttributes = new LinkedHashSet<>();

}
