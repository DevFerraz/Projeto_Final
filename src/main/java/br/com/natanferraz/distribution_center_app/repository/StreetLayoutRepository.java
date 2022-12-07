package br.com.natanferraz.distribution_center_app.repository;

import br.com.natanferraz.distribution_center_app.model.StreetLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface StreetLayoutRepository extends JpaRepository<StreetLayout, UUID> {
    public boolean existsByStreet(String street);
}
