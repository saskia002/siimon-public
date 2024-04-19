package siimon.core.api.module.testid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.testid.model.LangLevelModel;

@Repository
public interface LangLevelRepository extends JpaRepository<LangLevelModel, Integer> { }
