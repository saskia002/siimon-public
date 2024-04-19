package siimon.core.api.module.testid.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "test_parts", schema = "testid",
	indexes = {
		@Index(name = "test_parts_idx_2", columnList = "part_id"),
		@Index(name = "test_parts_idx_1", columnList = "test_id")
	}
)
public class TestPartModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "test_id", nullable = false)
	private TestModel test;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "part_id", nullable = false)
	private PartModel part;

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

	@JsonManagedReference
	@OneToMany(mappedBy = "testPart")
	private Set<ResultModel> results = new LinkedHashSet<>();

	@PrePersist protected void onCreate() { deleted = false; disabled = false; }
	@PreUpdate protected void onUpdate() { edited = Instant.now(); }

}
