package br.com.natanferraz.distribution_center_app.controller;

import br.com.natanferraz.distribution_center_app.model.Dimension;
import br.com.natanferraz.distribution_center_app.repository.DimensionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/dimension")
public class DimensionController {
    @Autowired
    DimensionRepository dimensionRepository;

        @PostMapping("/create")
        @ResponseStatus(HttpStatus.CREATED)
        public void create(@RequestBody Dimension dimension) {
            dimensionRepository.save(dimension);
            log.info("Dimension created");
        }

        @GetMapping("/{id}")
        public Optional<Dimension> read(@PathVariable Long id) {
            log.info("Dimension searched by id");
            return dimensionRepository.findById(id);
        }

        @PutMapping("/{id}")
        public void update(@RequestBody Dimension dimension) {
            dimensionRepository.save(dimension);
            log.info("Dimension updated");
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            dimensionRepository.deleteById(id);
            log.info("Dimension deleted");
        }

        @GetMapping()
        public List<Dimension> list() {
            log.info("Dimension list showed");
            return dimensionRepository.findAll();
        }
    }
