package siimon.core.api.module.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.auth.model.AuthorityModel;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityModel, Integer> {

	AuthorityModel findByAuthority(String authority);

}
