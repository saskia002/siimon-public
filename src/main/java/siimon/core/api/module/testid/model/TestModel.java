package siimon.core.api.module.testid.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@SQLDelete(sql = "UPDATE test SET deleted = true WHERE id = ?")
@Table(name = "test", schema = "testid")
public class TestModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Size(max = 255)
	@Column(name = "protected_key")
	private String protectedKey;

	@Size(max = 500)
	@NotNull
	@Column(name = "title", nullable = false, length = 500)
	private String title;

	@Size(max = 25000)
	@NotNull
	@Column(name = "description", nullable = false, length = 25000)
	private String description;

	@Column(name = "protected")
	private Boolean protectedField;

	@Column(name = "disabled")
	private Boolean disabled;

	@Column(name = "deleted", nullable = false)
	private Boolean deleted;

	@CreationTimestamp
	@Column(name = "created", updatable = false)
	private Instant created;

	@UpdateTimestamp
	@Column(name = "edited")
	private Instant edited;

//	@OneToMany(mappedBy = "testModel", orphanRemoval = true, cascade = CascadeType.PERSIST)
//	private Set<TestRightsModel> testRightModels = new LinkedHashSet<>();

	@JsonBackReference
	@OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<TestPartModel> testParts = new LinkedHashSet<>();

	@PrePersist
	protected void onCreate() { deleted = false; disabled = false; protectedField = true; }

	@PreUpdate
	protected void onUpdate() { edited = Instant.now(); }

}
