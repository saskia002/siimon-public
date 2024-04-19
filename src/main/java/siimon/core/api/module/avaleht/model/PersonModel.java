package siimon.core.api.module.avaleht.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "person", schema = "avaleht")
@Where(clause = "deleted = false")
@SQLUpdate(sql = "UPDATE person SET full_name = ?, mail = ?, phone = ?, idcode = ?, deleted = ?, edited = ? WHERE id = ? AND deleted = false")
@SQLDelete(sql = "UPDATE person SET deleted = true WHERE id = ? AND deleted = false")
public class PersonModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "mail", nullable = false)
	private String mail;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "deleted", nullable = false)
	private Boolean deleted;

	@Size(max = 50)
	@Column(name = "idcode", nullable = false, length = 50)
	private String idCode;

	@CreationTimestamp
	@Column(name = "created", updatable = false)
	private Instant created;

	@UpdateTimestamp
	@Column(name = "edited")
	private Instant edited;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CourseRegModel> courses = new ArrayList<>();

	@PrePersist protected void onCreate() { deleted = false; }

}
