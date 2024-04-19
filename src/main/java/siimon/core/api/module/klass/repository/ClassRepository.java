package siimon.core.api.module.klass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.klass.model.ClassModel;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<ClassModel, Integer> {

	Optional<ClassModel> findByKey(String key);

	boolean existsByKey(String key);

}
