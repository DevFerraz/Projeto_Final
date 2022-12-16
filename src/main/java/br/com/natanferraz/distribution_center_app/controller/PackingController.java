package br.com.natanferraz.distribution_center_app.controller;

import br.com.natanferraz.distribution_center_app.dto.PackingDto;
import br.com.natanferraz.distribution_center_app.model.CustomError;
import br.com.natanferraz.distribution_center_app.model.Packing;
import br.com.natanferraz.distribution_center_app.service.PackingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/packing")
public class PackingController {
    @Autowired
    PackingService packingService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody PackingDto packingDto) {
        if(packingService.existsByDescription(packingDto.getDescription().toUpperCase())){

            CustomError error = new CustomError( HttpStatus.CONFLICT,
                    "Conflict: Packing already created");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(error);
        }
        Packing packing = new Packing();
        BeanUtils.copyProperties(packingDto, packing);
        log.info("Packing created");
        return ResponseEntity.status(HttpStatus.CREATED).body(packingService.save(packing));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@PathVariable(value = "id") UUID id) {
        Optional<Packing> packingOptional = packingService.findById(id);
        log.info("Packing searched by id");
        if(packingOptional.isEmpty()){

            CustomError error = new CustomError( HttpStatus.NOT_FOUND,
                    "Not Found: Packing not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        log.info("Packing found by id");
        return ResponseEntity.status(HttpStatus.OK).body(packingOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                         @RequestBody PackingDto packingDto) {
        Optional<Packing> packingOptional = packingService.findById(id);
        if(packingOptional.isEmpty()){

            CustomError error = new CustomError( HttpStatus.NOT_FOUND,
                    "Not Found: Packing not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        Packing packing = new Packing();
        BeanUtils.copyProperties(packingDto, packing);
        packing.setId(packingOptional.get().getId());
        packing.setRegistrationDate(packingOptional.get().registrationDate);
        return ResponseEntity.status(HttpStatus.OK).body(packingService.save(packing));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        Optional<Packing> packingOptional = packingService.findById(id);
        log.info("Packing searched by id");
        if(packingOptional.isEmpty()){

            CustomError error = new CustomError( HttpStatus.NOT_FOUND,
                    "Not Found: Packing not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        log.info("Packing found by id");
        Packing packing = packingOptional.get();
        if(packing.getProducts().size() > 0){
            CustomError error = new CustomError(HttpStatus.PRECONDITION_REQUIRED,
                    "This Packing has related products");
            return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED)
                    .body(error);
        }
        packingService.delete((packing));
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping()
    public ResponseEntity<Page<Packing>> list(@PageableDefault(size = 5, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Packing list showed");
        return ResponseEntity.status(HttpStatus.OK).body(packingService.findAll(pageable));
    }
}

