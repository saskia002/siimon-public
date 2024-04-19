package siimon.core.api.module.testid.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.Instant;
import java.time.LocalTime;
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
@SQLDelete(sql = "UPDATE question SET deleted = true WHERE id = ?")
@Table(name = "question", schema = "testid", indexes = {@Index(name = "question_idx_1", columnList = "part_id")})
public class QuestionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "max_time")
	private LocalTime maxTime;

	@Size(max = 25000)
	@NotNull
	@Column(name = "question", nullable = false, length = 25000)
	private String question;

	@NotNull
	@Column(name = "points", nullable = false, precision = 5)
	private Double points;

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

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "part_id", nullable = false)
	private PartModel part;

	@OneToMany(mappedBy = "question")
	private Set<AnswerModel> answers = new LinkedHashSet<>();

	@PrePersist protected void onCreate() { deleted = false; disabled = false; }
	@PreUpdate protected void onUpdate() { edited = Instant.now(); }

}
