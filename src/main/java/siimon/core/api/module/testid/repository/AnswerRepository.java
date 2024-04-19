package siimon.core.api.module.testid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.testid.model.AnswerModel;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerModel, Integer> {

	Optional<Set<AnswerModel>> findByQuestionId(Integer questionId);

	Optional<AnswerModel> findByIdAndQuestionId(Integer id, Integer questionId);

}
