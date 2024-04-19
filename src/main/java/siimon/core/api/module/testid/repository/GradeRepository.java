package siimon.core.api.module.testid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.testid.model.GradeModel;

import java.util.Optional;
import java.util.Set;

@Repository
public interface GradeRepository extends JpaRepository<GradeModel, Integer> {

	Optional<Set<GradeModel>> findByPartId(Integer partId);

}
