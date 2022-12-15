package br.com.natanferraz.distribution_center_app.controller;

import br.com.natanferraz.distribution_center_app.dto.ProductDto;
import br.com.natanferraz.distribution_center_app.model.Packing;
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
import br.com.natanferraz.distribution_center_app.model.CustomError;
import java.time.LocalDateTime;
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
            CustomError error = new CustomError(HttpStatus.CONFLICT,
                    "Conflict: Batch already created");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(error);
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);

        Packing packing = productService.findPackingByDescription(productDto.getPacking().getDescription());

        if(packing != null)
            product.setPacking(packing);

        log.info("Product created");
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@PathVariable(value = "id") UUID id) {
        Optional<Product> productOptional = productService.findById(id);
        log.info("Product searched by id");
        if (productOptional.isEmpty()) {

            CustomError error = new CustomError(HttpStatus.NOT_FOUND,
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
        if (productOptional.isEmpty()) {
            CustomError error = new CustomError(HttpStatus.NOT_FOUND,
                    "Not Found: Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        log.info("Product found by id");
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setLastUpdateDate(LocalDateTime.now());
        product.setId(productOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        Optional<Product> productOptional = productService.findById(id);
        log.info("Product searched by id");
        if (productOptional.isEmpty()) {
            CustomError error = new CustomError(HttpStatus.NOT_FOUND,
                    "Not Found: Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        log.info("Product found by id");
        productService.delete((productOptional.get()));
        CustomError error = new CustomError(HttpStatus.NO_CONTENT,
                "Product deleted successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(error);
    }

    @GetMapping()
    public ResponseEntity<Page<Product>> list(@PageableDefault(size = 5, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Product list showed");
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(pageable));
    }

    @PutMapping("/receive/{id}/{qty}")
    public ResponseEntity<Object> receive(@PathVariable(value = "id") UUID id, @PathVariable(value = "qty") Integer qty) {
        Optional<Product> productOptional = productService.findById(id);
        if (qty <= 0) {
            CustomError error = new CustomError(HttpStatus.BAD_REQUEST,
                    "Quantity must be greater than zero");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        if (productOptional.isEmpty()) {
            CustomError error = new CustomError(HttpStatus.NOT_FOUND,
                    "Not Found: Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        log.info("Product found by id");
        Product product = productOptional.get();
        product.increaseQuantity(qty);
        product.setLastUpdateDate(LocalDateTime.now());
        productService.save(product);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/dispatch/{id}/{qty}")
    public ResponseEntity<Object> decrease(@PathVariable(value = "id") UUID id,
                                           @PathVariable(value = "qty") Integer qty) {
        Optional<Product> productOptional = productService.findById(id);
        if (qty <= 0) {
            CustomError error = new CustomError(HttpStatus.BAD_REQUEST,
                    "Quantity must be greater than zero");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        if (productOptional.isEmpty()) {
            CustomError error = new CustomError(HttpStatus.NOT_FOUND,
                    "Not Found: Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        log.info("Product found by id");
        Product product = productOptional.get();
        product.decreaseQuantity(qty);
        product.setLastUpdateDate(LocalDateTime.now());
        productService.save(product);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
