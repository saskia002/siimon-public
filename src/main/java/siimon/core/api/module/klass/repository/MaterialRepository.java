package siimon.core.api.module.klass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.klass.model.MaterialModel;
import siimon.core.api.module.klass.model.TeacherModel;

@Repository
public interface MaterialRepository extends JpaRepository<MaterialModel, Integer> {

}
