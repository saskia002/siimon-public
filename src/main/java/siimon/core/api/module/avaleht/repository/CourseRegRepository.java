package siimon.core.api.module.avaleht.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.avaleht.model.CourseRegModel;

import java.util.List;

@Repository
public interface CourseRegRepository extends JpaRepository<CourseRegModel, Integer> {


	@Query("SELECT cr FROM CourseRegModel cr WHERE  cr.id = :id")
	CourseRegModel findByCourseId(@Param("id") Integer id);


}
