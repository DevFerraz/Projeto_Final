package br.com.natanferraz.distribution_center_app.repository;

import br.com.natanferraz.distribution_center_app.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository

public interface UserRepository extends JpaRepository<UserModel, String> {
    @Override
    boolean existsById(String username);

    Optional<UserModel> findByUsername(String username);
}
