package br.com.natanferraz.distribution_center_app.controller;

import br.com.natanferraz.distribution_center_app.model.Packing;
import br.com.natanferraz.distribution_center_app.repository.PackingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/packing")
public class PackingController {
    @Autowired
    PackingRepository packingRepository;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Packing packing) {
        packingRepository.save(packing);
        log.info("Packing created");
    }

    @GetMapping("/{id}")
    public Optional<Packing> read(@PathVariable Long id) {
        log.info("Packing searched by id");
        return packingRepository.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Packing packing) {
        packingRepository.save(packing);
        log.info("Packing updated");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        packingRepository.deleteById(id);
        log.info("Packing deleted");
    }

    @GetMapping()
    public List<Packing> list() {
        log.info("Packing list showed");
        return packingRepository.findAll();
    }
}

