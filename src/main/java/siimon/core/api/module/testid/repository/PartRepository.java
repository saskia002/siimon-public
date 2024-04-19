package siimon.core.api.module.testid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.testid.model.PartModel;

import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<PartModel, Integer> {

	Optional<PartModel> findByIdAndStep(Integer id, Integer step);

}
