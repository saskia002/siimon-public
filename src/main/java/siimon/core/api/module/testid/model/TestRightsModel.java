package siimon.core.api.module.testid.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "test_rights", schema = "testid", indexes = {@Index(name = "test_rights_idx_1", columnList = "test_id")})
public class TestRightsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@NotNull
	@Column(name = "user_id", nullable = false)
	private Integer userId;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "test_id", nullable = false)
	private TestModel testModel;

	@NotNull
	@Column(name = "deleted", nullable = false)
	private Boolean deleted = false;

	@Column(name = "created")
	private Instant created;

	@Column(name = "edited")
	private Instant edited;

}
