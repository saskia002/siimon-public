package siimon.core.api.module.avaleht.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.Instant;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "course_registration", schema = "avaleht")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE course_registration SET deleted = true WHERE id = ? AND deleted = false")
public class CourseRegModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "course_id", nullable = false)
	private Integer courseId;

	@Column(name = "person_id", nullable = false)
	private Integer personId;

	@Column(name = "deleted")
	private Boolean deleted;

	@CreationTimestamp
	@Column(name = "created", updatable = false)
	private Instant created;

	@UpdateTimestamp
	@Column(name = "edited")
	private Instant edited;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "course_id", insertable = false, updatable = false)
	private CourseModel course;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "person_id", insertable = false, updatable = false)
	private PersonModel person;

	@PrePersist protected void onCreate() { deleted = false; }

}
