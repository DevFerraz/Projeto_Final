package br.com.natanferraz.distribution_center_app.repository;

import br.com.natanferraz.distribution_center_app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByBatch(String batch);
}