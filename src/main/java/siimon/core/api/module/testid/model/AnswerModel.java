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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE answer SET deleted = true WHERE id = ?")
@Table(name = "answer", schema = "testid", indexes = {@Index(name = "answer_idx_qfk", columnList = "question_id")})
public class AnswerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "question_id", nullable = false)
	private QuestionModel question;

	@NotNull
	@Column(name = "correct", nullable = false)
	private Boolean correct;

	@Size(max = 255)
	@NotNull
	@Column(name = "answer", nullable = false)
	private String answer;

	@Column(name = "disabled", nullable = false)
	private Boolean disabled;

	@Column(name = "deleted", nullable = false)
	private Boolean deleted;

	@CreationTimestamp
	@Column(name = "created", updatable = false)
	private Instant created;

	@UpdateTimestamp
	@Column(name = "edited")
	private Instant edited;

	@PrePersist protected void onCreate() { deleted = false; disabled = false; correct = false; }
	@PreUpdate protected void onUpdate() { edited = Instant.now(); }

}
