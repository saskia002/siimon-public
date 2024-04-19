package siimon.core.api.module.auth.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ? AND deleted = false")
@Where(clause = "deleted = false")
@Table(name = "users", schema = "auth")
public class UserModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Size(max = 50)
	@Column(name = "username", nullable = false, length = 50)
	private String username;

	@JsonIgnore
	@Size(max = 500)
	@Column(name = "password", nullable = false, length = 500)
	private String password;

	@JsonIgnore
	@Column(name = "deleted")
	private Boolean deleted;

	@CreationTimestamp
	@Column(name = "created", updatable = false)
	private Instant created;

	@UpdateTimestamp
	@Column(name = "edited")
	private Instant edited;


	@JsonManagedReference
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "users_authorities", schema = "auth", joinColumns = @JoinColumn(name = "users_id"),
			inverseJoinColumns =
	@JoinColumn(name = "authorities_id"))
	private Set<AuthorityModel> authorities = new LinkedHashSet<>();

	@JsonBackReference
	@OneToMany(mappedBy = "users")
	private Set<UsersAuthorityModel> usersAuthorities = new LinkedHashSet<>();

	public void addAuthority(AuthorityModel authority) {
		this.authorities.add(authority);
		authority.getUsers().add(this);
	}

	public void removeAuthority(AuthorityModel authority) {
		this.authorities.remove(authority);
		authority.getUsers().remove(this);
	}

	public static UserModel createNew(String username, String password) {
		return UserModel.builder()
				.username(username)
				.password(password)
				.build();
	}

	@PrePersist protected void onCreate() { deleted = false; }

	@PreRemove
	private void preRemove() {
		for (AuthorityModel authority : authorities) {
			authority.getUsers().remove(this);
		}
	}

}
