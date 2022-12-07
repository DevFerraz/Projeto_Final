package br.com.natanferraz.distribution_center_app.repository;

import br.com.natanferraz.distribution_center_app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    public boolean existsByBatch(String batch);
}