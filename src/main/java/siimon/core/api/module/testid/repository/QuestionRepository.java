package siimon.core.api.module.testid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.testid.model.QuestionModel;

import java.util.Optional;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionModel, Integer> {

	Optional<Set<QuestionModel>> findByPartId(Integer partId);

	Optional<Set<QuestionModel>> findByPartStep(Integer step);

}
