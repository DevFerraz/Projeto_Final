package br.com.natanferraz.distribution_center_app.controller;

import br.com.natanferraz.distribution_center_app.model.StreetLayout;
import br.com.natanferraz.distribution_center_app.repository.StreetLayoutRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/streetlayout")
public class StreetLayoutController{
    @Autowired
    StreetLayoutRepository streetLayoutRepository;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody StreetLayout streetLayout) {
        streetLayoutRepository.save(streetLayout);
        log.info("Street layout created");
    }

    @GetMapping("/{id}")
    public Optional<StreetLayout> read(@PathVariable Long id) {
        log.info("Street layout searched by id");
        return streetLayoutRepository.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody StreetLayout streetLayout) {
        streetLayoutRepository.save(streetLayout);
        log.info("Street layout updated");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        streetLayoutRepository.deleteById(id);
        log.info("Street layout deleted");
    }

    @GetMapping()
    public List<StreetLayout> list() {
        log.info("Street layout list showed");
        return streetLayoutRepository.findAll();
    }
}

