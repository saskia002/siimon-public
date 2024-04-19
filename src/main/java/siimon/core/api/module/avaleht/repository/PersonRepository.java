package siimon.core.api.module.avaleht.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.avaleht.model.PersonModel;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Integer> {}
