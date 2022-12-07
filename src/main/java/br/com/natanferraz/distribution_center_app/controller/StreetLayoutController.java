package br.com.natanferraz.distribution_center_app.controller;

import br.com.natanferraz.distribution_center_app.dto.StreetLayoutDto;
import br.com.natanferraz.distribution_center_app.model.StreetLayout;
import br.com.natanferraz.distribution_center_app.service.StreetLayoutService;
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
@RequestMapping("/streetlayout")
public class StreetLayoutController{
    @Autowired
    StreetLayoutService streetLayoutService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody StreetLayoutDto streetLayoutDto) {
        if(streetLayoutService.existsByStreet(streetLayoutDto.getStreet())){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflict: Street already created");
        }
        StreetLayout streetLayout = new StreetLayout();
        BeanUtils.copyProperties(streetLayoutDto, streetLayout);
        streetLayout.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        log.info("Street layout created");
        return ResponseEntity.status(HttpStatus.CREATED).body(streetLayoutService
                .save(streetLayout));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@PathVariable(value = "id") UUID id) {
        Optional<StreetLayout> streetLayoutOptional = streetLayoutService.findById(id);
        log.info("Street Layout searched by id");
        if(!streetLayoutOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Street Layout not found");
        }
        log.info("Street Layout found by id");
        return ResponseEntity.status(HttpStatus.OK).body(streetLayoutOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                         @RequestBody StreetLayoutDto streetLayoutDto) {
        Optional<StreetLayout> streetLayoutOptional = streetLayoutService.findById(id);
        if(!streetLayoutOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Street Layout not found");
        }
        StreetLayout streetLayout = new StreetLayout();
        BeanUtils.copyProperties(streetLayoutDto, streetLayout);
        streetLayout.setId(streetLayoutOptional.get().getId());
        streetLayout.setRegistrationDate(streetLayoutOptional.get().getRegistrationDate);
        return ResponseEntity.status(HttpStatus.OK).body(streetLayoutService.save(streetLayout));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        Optional<StreetLayout> streetLayoutOptional = streetLayoutService.findById(id);
        log.info("Street Layout searched by id");
        if(!streetLayoutOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Street Layout not found");
        }
        log.info("Street Layout found by id");
        streetLayoutService.delete((streetLayoutOptional.get()));
        return ResponseEntity.status(HttpStatus.OK).body("Street Layout deleted successfully");
    }

    @GetMapping()
    public ResponseEntity<Page<StreetLayout>> list(@PageableDefault(page = 0, size = 5, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Street Layout list showed");
        return ResponseEntity.status(HttpStatus.OK).body(streetLayoutService.findAll(pageable));
    }
}

