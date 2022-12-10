package br.com.natanferraz.distribution_center_app.controller;

import br.com.natanferraz.distribution_center_app.dto.ProductDto;
import br.com.natanferraz.distribution_center_app.model.CustomError;
import br.com.natanferraz.distribution_center_app.model.Product;
import br.com.natanferraz.distribution_center_app.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/product", produces = "application/json")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ProductDto productDto) {
        if(productService.existsByBatch(productDto.getBatch())){

            CustomError error = new CustomError("CONFLICT", HttpStatus.CONFLICT,
                    "Conflict: Batch already created");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(error);
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        log.info("Product created");
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@PathVariable(value = "id") UUID id) {
        Optional<Product> productOptional = productService.findById(id);
        log.info("Product searched by id");
        if(productOptional.isEmpty()){

            CustomError error = new CustomError("NOT_FOUND", HttpStatus.NOT_FOUND,
                    "Not Found: Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        log.info("Product found by id");
        return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                         @RequestBody ProductDto productDto) {
        Optional<Product> productOptional = productService.findById(id);
        if(productOptional.isEmpty()){
            CustomError error = new CustomError("NOT_FOUND", HttpStatus.NOT_FOUND,
                    "Not Found: Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        log.info("Product found by id");
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setId(productOptional.get().getId());
        product.setRegistrationDate(productOptional.get().registrationDate);
        return ResponseEntity.status(HttpStatus.OK).body(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        Optional<Product> productOptional = productService.findById(id);
        log.info("Product searched by id");
        if(productOptional.isEmpty()){
            CustomError error = new CustomError("NOT_FOUND", HttpStatus.NOT_FOUND,
                    "Not Found: Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        log.info("Product found by id");
        productService.delete((productOptional.get()));
        CustomError error = new CustomError("DELETED", HttpStatus.NO_CONTENT,
                "Product deleted successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(error);
    }

    @GetMapping()
    public ResponseEntity<Page<Product>> list(@PageableDefault(page = 0, size = 5, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Product list showed");
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(pageable));
    }


}

