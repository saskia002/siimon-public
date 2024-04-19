package siimon.core.api.module.auth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE users_authorities SET deleted = true WHERE id = ?")
@Table(name = "users_authorities", schema = "auth", indexes = {
		@Index(name = "users_authorities_idx_1", columnList = "users_id"),
		@Index(name = "users_authorities_idx_2", columnList = "authorities_id")
})
public class UsersAuthorityModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "deleted")
	private Boolean deleted;

	@NotNull
	@CreationTimestamp
	@Column(name = "created", updatable = false)
	private Instant created;

	@UpdateTimestamp
	@Column(name = "edited")
	private Instant edited;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "users_id", nullable = false, updatable = false)
	private UserModel users;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "authorities_id", nullable = false, updatable = false)
	private AuthorityModel authorities;

	@PrePersist protected void onCreate() { deleted = false; created = Instant.now(); edited = Instant.now(); }
	@PreUpdate protected void onUpdate() { edited = Instant.now(); }


}
