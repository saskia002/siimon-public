package siimon.core.api.module.testid.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

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
@Table(name = "lang_level", schema = "testid")
public class LangLevelModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Size(max = 25)
	@NotNull
	@Column(name = "value", nullable = false, length = 25)
	private String value;

	@NotNull
	@Column(name = "deleted", nullable = false)
	private Boolean deleted = false;

	@Column(name = "created")
	private Instant created;

	@Column(name = "edited")
	private Instant edited;

	@OneToMany(mappedBy = "langLevel")
	private Set<GradeModel> grades = new LinkedHashSet<>();

	@OneToMany(mappedBy = "langLevel")
	private Set<ResultModel> results = new LinkedHashSet<>();

}
