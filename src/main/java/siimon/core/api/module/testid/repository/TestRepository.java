package siimon.core.api.module.testid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.testid.model.TestModel;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<TestModel, Integer> {

	Optional<TestModel> findByProtectedKey(String key);

}
