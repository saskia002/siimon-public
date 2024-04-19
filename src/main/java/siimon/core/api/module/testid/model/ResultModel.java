package siimon.core.api.module.testid.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE result SET deleted = true WHERE id = ?")
@Table(name = "result", schema = "testid",
	indexes = {
		@Index(name = "resulidx_3", columnList = "lang_level_id"),
		@Index(name = "resulidx_1", columnList = "test_parts_id"),
		@Index(name = "resulidx_2", columnList = "person_id")
	}
)
public class ResultModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "test_parts_id", nullable = false)
	private TestPartModel testPart;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "person_id", nullable = false)
	private TPersonModel person;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "lang_level_id", nullable = false)
	private LangLevelModel langLevel;

	@Column(name = "total_points", nullable = false, precision = 5)
	private Double totalPoints;

	@Column(name = "max_points", nullable = false, precision = 5)
	private Double maxPoints;

	@Column(name = "answers")
	@JdbcTypeCode(SqlTypes.JSON)
	private Map<String, Integer> answers;

	@Column(name = "deleted", nullable = false)
	private Boolean deleted = false;

	@Column(name = "created")
	private Instant created;

	@Column(name = "edited")
	private Instant edited;

	@PrePersist
	protected void onCreate() { deleted = false; created = Instant.now(); edited = Instant.now(); }

	@PreUpdate
	protected void onUpdate() { edited = Instant.now(); }

}
