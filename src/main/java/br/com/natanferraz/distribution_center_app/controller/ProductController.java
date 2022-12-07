package br.com.natanferraz.distribution_center_app.controller;

import br.com.natanferraz.distribution_center_app.model.Product;
import br.com.natanferraz.distribution_center_app.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/product", produces = "application/json")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Product product) {
        productRepository.save(product);
        log.info("Product created");
    }

    @GetMapping("/{id}")
    public Optional<Product> read(@PathVariable Long id) {
        log.info("Product searched by id");
        return productRepository.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Product product) {
        productRepository.save(product);
        log.info("Product updated");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        log.info("Product deleted");
    }

    @GetMapping()
    public List<Product> list() {
        return productRepository.findAll();
    }

}

