package siimon.core.api.module.avaleht.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.avaleht.model.CourseModel;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, Integer> {}
