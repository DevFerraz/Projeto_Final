package br.com.natanferraz.distribution_center_app.controller;

import br.com.natanferraz.distribution_center_app.dto.PalletDto;
import br.com.natanferraz.distribution_center_app.model.Pallet;
import br.com.natanferraz.distribution_center_app.service.PalletService;
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
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/pallet")
public class PalletController {
    @Autowired
    PalletService palletService;
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody PalletDto palletDto) {
        Pallet pallet = new Pallet();
        BeanUtils.copyProperties(palletDto, pallet);
        pallet.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        log.info("Pallet created");
        return ResponseEntity.status(HttpStatus.CREATED).body(palletService.save(pallet));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@PathVariable(value = "id") UUID id) {
        Optional<Pallet> palletOptional = palletService.findById(id);
        log.info("Pallet searched by id");
        if (!palletOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pallet not found");
        }
        log.info("Pallet found by id");
        return ResponseEntity.status(HttpStatus.OK).body(palletOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                         @RequestBody PalletDto palletDto) {
        Optional<Pallet> palletOptional = palletService.findById(id);
        if(!palletOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pallet not found");
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
        if (!palletOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pallet not found");
        }
        palletService.delete((palletOptional.get()));
        return ResponseEntity.status(HttpStatus.OK).body("Pallet deleted successfully");
    }

    @GetMapping()
    public ResponseEntity<Page<Pallet>> list(@PageableDefault(page = 0, size = 5, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Pallet list showed");
        return ResponseEntity.status(HttpStatus.OK).body(palletService.findAll(pageable));
    }
}

