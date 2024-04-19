package siimon.core.api.module.auth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spring_session_attributes", schema = "public")
public class SpringSessionAttribute {
	
	@EmbeddedId
	private SpringSessionAttributeId id;

	@MapsId("sessionPrimaryId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "session_primary_id", nullable = false)
	private SpringSession sessionPrimary;

	@NotNull
	@Column(name = "attribute_bytes", nullable = false)
	private byte[] attributeBytes;

}
