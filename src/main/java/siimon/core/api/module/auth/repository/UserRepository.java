package siimon.core.api.module.auth.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import siimon.core.api.module.auth.model.UserModel;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

	@NonNull Optional<UserModel> findById(@NonNull Integer id);
	Optional<UserModel> findByUsername(String username);

	Optional<UserModel> findByUsernameAndPassword(String username, String password);

}
