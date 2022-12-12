package br.com.natanferraz.distribution_center_app.controller;

import br.com.natanferraz.distribution_center_app.dto.PalletDto;
import br.com.natanferraz.distribution_center_app.enums.PalletStatus;
import br.com.natanferraz.distribution_center_app.model.CustomError;
import br.com.natanferraz.distribution_center_app.model.Pallet;
import br.com.natanferraz.distribution_center_app.model.Product;
import br.com.natanferraz.distribution_center_app.service.PalletService;
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
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/pallet")
public class PalletController {
    @Autowired
    PalletService palletService;
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody PalletDto palletDto) {
        if(palletService.existsByHeightAndAndWeightAndLengthAndWidth(palletDto.getHeight(), palletDto.getWeight(),
                palletDto.getLength(), palletDto.getWidth())){

            CustomError error = new CustomError(HttpStatus.CONFLICT,
                    "Conflict: Pallet's dimensions already created");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(error);
        }
        Pallet pallet = new Pallet();
        BeanUtils.copyProperties(palletDto, pallet);
        log.info("Pallet created");
        return ResponseEntity.status(HttpStatus.CREATED).body(palletService.save(pallet));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@PathVariable(value = "id") UUID id) {
        Optional<Pallet> palletOptional = palletService.findById(id);
        log.info("Pallet searched by id");
        if (palletOptional.isEmpty()) {

            CustomError error = new CustomError(HttpStatus.NOT_FOUND,
                    "Not Found: Pallet not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        log.info("Pallet found by id");
        return ResponseEntity.status(HttpStatus.OK).body(palletOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                         @RequestBody PalletDto palletDto) {
        Optional<Pallet> palletOptional = palletService.findById(id);
        if(palletOptional.isEmpty()){

            CustomError error = new CustomError(HttpStatus.NOT_FOUND,
                    "Not Found: Pallet not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        Pallet pallet = new Pallet();
        BeanUtils.copyProperties(palletDto, pallet);
        pallet.setId(palletOptional.get().getId());
        pallet.setRegistrationDate(palletOptional.get().registrationDate);
        return ResponseEntity.status(HttpStatus.OK).body(palletService.save(pallet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        Optional<Pallet> palletOptional = palletService.findById(id);
        log.info("Pallet searched by id");
        if (palletOptional.isEmpty()) {

            CustomError error = new CustomError(HttpStatus.NOT_FOUND,
                    "Not Found: Pallet not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        palletService.delete((palletOptional.get()));
        CustomError error = new CustomError(HttpStatus.NO_CONTENT,
                "Pallet deleted successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(error);
    }

    @GetMapping()
    public ResponseEntity<Page<Pallet>> list(@PageableDefault(size = 5, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Pallet list showed");
        return ResponseEntity.status(HttpStatus.OK).body(palletService.findAll(pageable));
    }

    @PutMapping("/product-allocation/{palletId}/{productId}/{qty}")
    public ResponseEntity<Object> productAllocation(@PathVariable(value = "palletId") UUID palletId,
                                                    @PathVariable(value = "productId") UUID productId,
                                                    @PathVariable(value = "qty") Integer qty) {


        if (palletService.findById(palletId).isEmpty()) {
            CustomError error = new CustomError(HttpStatus.NOT_FOUND,
                    "Not Found: Pallet not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        if (productService.findById(productId).isEmpty()) {
            CustomError error = new CustomError(HttpStatus.NOT_FOUND,
                    "Not Found: Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        Pallet pallet = palletService.findById(palletId).get();
        Product product = productService.findById(productId).get();
        if (product.getQuantity() < qty) {
            CustomError error = new CustomError(HttpStatus.BAD_REQUEST,
                    "Insufficient product quantity to perform this operation");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
        if(!(pallet.getStatus() == PalletStatus.IN_USE && pallet.getProduct().getId()==productId)) {
            CustomError error = new CustomError(HttpStatus.BAD_REQUEST,
                    "Pallet filled with another product");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
        if(!pallet.isAvailable()) {
            CustomError error = new CustomError(HttpStatus.BAD_REQUEST,
                    "Pallet not available");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }

        product.decreaseQuantity(qty);
        pallet.includeProduct(product, qty);

        log.info("Product successfully included");

        product.setLastUpdateDate(LocalDateTime.now());
        pallet.setLastUpdateDate(LocalDateTime.now());
        productService.save(product);
        palletService.save(pallet);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

}

