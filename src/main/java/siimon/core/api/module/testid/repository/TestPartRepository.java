package siimon.core.api.module.testid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.testid.model.TestPartModel;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TestPartRepository extends JpaRepository<TestPartModel, Integer> {

	Optional<Set<TestPartModel>> findByTestId(Integer testId);

	Optional<Set<TestPartModel>> findByTestProtectedKey(String testKey);

	Optional<TestPartModel> findByTestIdAndPartStep(Integer testId, Integer partStep);

}
