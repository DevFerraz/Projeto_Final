package br.com.natanferraz.distribution_center_app.repository;

import br.com.natanferraz.distribution_center_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

   Optional<User> findByPhoneNumber(String phoneNumber);

    Boolean existsByName(String name);
    Optional<User> findByName(String name);
}
