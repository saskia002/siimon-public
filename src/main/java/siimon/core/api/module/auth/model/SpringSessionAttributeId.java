package siimon.core.api.module.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Embeddable
@Table(name = "spring_session_attributes_id", schema = "public")
public class SpringSessionAttributeId implements Serializable {

	@Serial
	private static final long serialVersionUID = - 5713330129424820516L;
	@Size(max = 36)
	@NotNull
	@Column(name = "session_primary_id", nullable = false, length = 36)
	private String sessionPrimaryId;

	@Size(max = 200)
	@NotNull
	@Column(name = "attribute_name", nullable = false, length = 200)
	private String attributeName;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		SpringSessionAttributeId entity = (SpringSessionAttributeId) o;
		return Objects.equals(this.sessionPrimaryId, entity.sessionPrimaryId) && Objects.equals(this.attributeName, entity.attributeName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sessionPrimaryId, attributeName);
	}

}
