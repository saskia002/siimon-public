package siimon.core.api.module.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.auth.model.UsersAuthorityModel;

@Repository
public interface UsersAuthorityRepository extends JpaRepository<UsersAuthorityModel, Integer> {

}
