package siimon.core.api.module.klass.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.Instant;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString(onlyExplicitlyIncluded = true)
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE class_material SET deleted = true WHERE id = ?")
@Table(name = "class_material", schema = "klass")
public class ClassMaterialModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "class_id", nullable = false)
	private ClassModel classModel;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "material_id", nullable = false)
	private MaterialModel materialModel;

	@Column(name = "deadline")
	private Instant deadline;

	@Column(name = "deleted", nullable = false)
	private Boolean deleted;

	@CreationTimestamp
	@Column(name = "created", updatable = false)
	private Instant created;

	@UpdateTimestamp
	@Column(name = "edited")
	private Instant edited;

	@PrePersist protected void onCreate() { deleted = false; }
	@PreUpdate protected void onUpdate() { edited = Instant.now(); }

}
