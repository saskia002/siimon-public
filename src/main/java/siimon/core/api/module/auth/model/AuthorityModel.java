package siimon.core.api.module.auth.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE authorities SET deleted = true WHERE id = ? AND deleted = false")
@Where(clause = "deleted = false")
@Table(name = "authorities", schema = "auth")
public class AuthorityModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Size(max = 50)
	@Column(name = "authority", nullable = false, length = 50)
	private String authority;

	@JsonIgnore
	@Column(name = "deleted")
	private Boolean deleted;

	@CreationTimestamp
	@Column(name = "created", updatable = false)
	private Instant created;

	@UpdateTimestamp
	@Column(name = "edited")
	private Instant edited;

	@JsonBackReference
	@ManyToMany(mappedBy = "authorities", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH})
	private Set<UserModel> users = new LinkedHashSet<>();

	@JsonBackReference
	@OneToMany(mappedBy = "authorities")
	private Set<UsersAuthorityModel> usersAuthorities = new LinkedHashSet<>();

	public static AuthorityModel createNew(String role) {
		return AuthorityModel.builder()
				.authority(role.toUpperCase())
				.build();
	}

	@PrePersist protected void onCreate() { deleted = false; }

}
