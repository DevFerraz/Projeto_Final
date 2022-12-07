package br.com.natanferraz.distribution_center_app.controller;

import br.com.natanferraz.distribution_center_app.model.Pallet;
import br.com.natanferraz.distribution_center_app.repository.PalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/pallet")
public class PalletController {
    @Autowired
    PalletRepository palletRepository;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Pallet pallet) {
        palletRepository.save(pallet);
        log.info("Pallet created");
    }

    @GetMapping("/{id}")
    public Optional<Pallet> read(@PathVariable Long id) {
        log.info("Pallet searched by id");
        return palletRepository.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Pallet pallet) {
        palletRepository.save(pallet);
        log.info("Pallet updated");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        palletRepository.deleteById(id);
        log.info("Pallet deleted");
    }

    @GetMapping()
    public List<Pallet> list() {
        log.info("Pallet list showed");
        return palletRepository.findAll();
    }
}

