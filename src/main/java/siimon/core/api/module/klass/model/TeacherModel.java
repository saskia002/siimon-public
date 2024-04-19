package siimon.core.api.module.klass.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE teacher SET deleted = true WHERE id = ?")
@Table(name = "teacher", schema = "klass")
public class TeacherModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Size(max = 500)
	@Column(name = "full_name", nullable = false, length = 500)
	private String fullName;

	@Size(max = 1000)
	@Column(name = "contact", length = 1000)
	private String contact;

	@Column(name = "deleted", nullable = false)
	private Boolean deleted;

	@CreationTimestamp
	@Column(name = "created", updatable = false)
	private Instant created;

	@UpdateTimestamp
	@Column(name = "edited")
	private Instant edited;

	@OneToMany(mappedBy = "teacherModel", fetch = FetchType.LAZY)
	private final Set<ClassTeacherModel> classTeacherModels = new LinkedHashSet<>();

	@PrePersist protected void onCreate() { deleted = false; }
	@PreUpdate protected void onUpdate() { edited = Instant.now(); }

}
