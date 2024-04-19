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
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE part SET deleted = true WHERE id = ?")
@Table(name = "part", schema = "testid")
public class PartModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Size(max = 1000)
	@NotNull
	@Column(name = "description", nullable = false, length = 1000)
	private String description;

	@Column(name = "max_time")
	private LocalTime maxTime;

	@Column(name = "step")
	private Integer step;

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

	@JsonBackReference
	@OneToMany(mappedBy = "part")
	private Set<QuestionModel> questions = new LinkedHashSet<>();

	@OneToMany(mappedBy = "part")
	private Set<GradeModel> grades = new LinkedHashSet<>();

	@OneToMany(mappedBy = "part")
	private Set<TestPartModel> testParts = new LinkedHashSet<>();


	@PrePersist protected void onCreate() { deleted = false; disabled = false; }
	@PreUpdate protected void onUpdate() { edited = Instant.now(); }

}
