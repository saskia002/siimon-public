package siimon.core.api.module.avaleht.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "course" , schema = "avaleht")
@Where(clause = "deleted = false")
@SQLUpdate(sql = "UPDATE course SET title_registration = ?, title = ?, description = ?, date = ?, links = ?, deleted = ?, edited = ? WHERE id = ? AND deleted = false")
@SQLDelete(sql = "UPDATE course SET deleted = true WHERE id = ? AND deleted = false")
public class CourseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Size(max = 1000)
	@NotNull
	@Column(name = "title_registration", nullable = false, length = 1000)
	private String titleRegistration;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "date", nullable = false)
	private String date;

	@ColumnTransformer(write = "to_json(?::json)")
	@Column(name = "links", nullable = false, columnDefinition = "json")
	private String links;

	@Column(name = "deleted")
	private Boolean deleted;

	@CreationTimestamp
	@Column(name = "created", updatable = false)
	private Instant created;

	@UpdateTimestamp
	@Column(name = "edited")
	private Instant edited;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CourseRegModel> persons = new ArrayList<>();

	@PrePersist protected void onCreate() { deleted = false; }

}
