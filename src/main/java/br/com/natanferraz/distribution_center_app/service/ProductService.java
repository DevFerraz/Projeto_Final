package br.com.natanferraz.distribution_center_app.service;

import br.com.natanferraz.distribution_center_app.model.Packing;
import br.com.natanferraz.distribution_center_app.model.Product;
import br.com.natanferraz.distribution_center_app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    PackingService packingService;
    @Transactional
    public Product save(Product product){
        return productRepository.save(product);
    }
    @Transactional
    public void delete(Product product){
        productRepository.delete(product);
    }
    public boolean existsByBatch(String batch) {
        return productRepository.existsByBatch(batch);
    }
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }
    public Packing findPackingByDescription(String description){
        return packingService.getByDescription(description);
    }
}
