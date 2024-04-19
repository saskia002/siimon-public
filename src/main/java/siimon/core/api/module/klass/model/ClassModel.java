package siimon.core.api.module.klass.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.Instant;
import java.time.LocalDate;
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
@SQLDelete(sql = "UPDATE class SET deleted = true WHERE id = ?")
@Table(name = "class", schema = "klass")
public class ClassModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Size(max = 255)
	@Column(name = "key", nullable = false)
	private String key;

	@Size(max = 1000)
	@Column(name = "title", nullable = false, length = 1000)
	private String title;

	@Column(name = "begins")
	private LocalDate begins;

	@Column(name = "ends")
	private LocalDate ends;

	@Column(name = "schedule")
	private String schedule;

	@Column(name = "meets")
	private String meets;

	@Column(name = "location")
	private String location;

	@Size(max = 25555)
	@Column(name = "description", length = 25555)
	private String description;

	@Column(name = "deleted", nullable = false)
	private Boolean deleted;

	@CreationTimestamp
	@Column(name = "created", updatable = false)
	private Instant created;

	@UpdateTimestamp
	@Column(name = "edited")
	private Instant edited;

	@OneToMany(mappedBy = "classModel", fetch = FetchType.LAZY)
	private Set<ClassRightModel> classRightModels = new LinkedHashSet<>();

	@OneToMany(mappedBy = "classModel", fetch = FetchType.LAZY)
	private Set<ClassMaterialModel> classMaterials = new LinkedHashSet<>();

	@OneToMany(mappedBy = "classModel", fetch = FetchType.LAZY)
	private Set<ClassTeacherModel> classTeacher = new LinkedHashSet<>();

	@JsonManagedReference
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinTable(
			name = "class_material",
			schema = "klass",
			joinColumns = @JoinColumn(name = "class_id"),
			inverseJoinColumns = @JoinColumn(name = "material_id")
	)
	private final Set<MaterialModel> materials = new LinkedHashSet<>();

	@JsonManagedReference
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = "class_teacher",
			schema = "klass",
			joinColumns = @JoinColumn(name = "class_id"),
			inverseJoinColumns = @JoinColumn(name = "teacher_id")
	)
	private final Set<TeacherModel> teachers = new LinkedHashSet<>();

	@PrePersist protected void onCreate() { deleted = false; }
	@PreUpdate protected void onUpdate() { edited = Instant.now(); }

}
