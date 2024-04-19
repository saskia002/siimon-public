package siimon.core.api.module.testid.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "grade", schema = "testid", indexes = {@Index(name = "grade_idx_2", columnList = "part_id"), @Index(name =
		"grade_idx_1", columnList = "lang_level_id")})
public class GradeModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "lang_level_id", nullable = false)
	private LangLevelModel langLevel;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "part_id", nullable = false)
	private PartModel part;

	@NotNull
	@Column(name = "min_result_percent", nullable = false, precision = 5, scale = 2)
	private BigDecimal minResultPercent;

	@NotNull
	@Column(name = "max_result_percent", nullable = false, precision = 5, scale = 2)
	private BigDecimal maxResultPercent;

	@NotNull
	@Column(name = "next_part", nullable = false)
	private Boolean nextPart = false;

	@NotNull
	@Column(name = "deleted", nullable = false)
	private Boolean deleted = false;

	@Column(name = "created")
	private Instant created;

	@Column(name = "edited")
	private Instant edited;

}
