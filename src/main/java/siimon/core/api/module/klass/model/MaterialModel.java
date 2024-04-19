package siimon.core.api.module.klass.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE material SET deleted = true WHERE id = ?")
@Table(name = "material", schema = "klass")
public class MaterialModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Size(max = 500)
	@Column(name = "title", nullable = false, length = 500)
	private String title;

	@Size(max = 500)
	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "links")
	@JdbcTypeCode(SqlTypes.JSON)
	private Map<String, Object> links;

	@Column(name = "deleted", nullable = false)
	private Boolean deleted;

	@CreationTimestamp
	@Column(name = "created", updatable = false)
	private Instant created;

	@UpdateTimestamp
	@Column(name = "edited")
	private Instant edited;

	@OneToMany(mappedBy = "materialModel", fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
	private final Set<ClassMaterialModel> classMaterials = new LinkedHashSet<>();

	@JsonBackReference
	@ManyToMany(mappedBy = "materials", fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private final Set<ClassModel> classes = new LinkedHashSet<>();

	@PrePersist protected void onCreate() { deleted = false; }
	@PreUpdate protected void onUpdate() { edited = Instant.now(); }

}
