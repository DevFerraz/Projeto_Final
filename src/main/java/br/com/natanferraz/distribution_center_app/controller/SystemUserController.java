//package br.com.natanferraz.distribution_center_app.controller;
//
//import br.com.natanferraz.distribution_center_app.dto.SystemUserDto;
//import br.com.natanferraz.distribution_center_app.model.SystemUser;
//import br.com.natanferraz.distribution_center_app.service.SystemUserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Optional;
//import java.util.UUID;
//
//@Slf4j
//@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RequestMapping("/users")
//public class SystemUserController {
//    @Autowired
//    SystemUserService systemUserService;
////    @PreAuthorize("hasAnyRole('DIRECTOR')")
//    @PostMapping("/create")
//    public ResponseEntity<Object> create(@RequestBody SystemUserDto systemUserDto) {
//        SystemUser systemUser = new SystemUser();
//        BeanUtils.copyProperties(systemUserDto, systemUser);
//        systemUser.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
//        log.info("System User created");
//        return ResponseEntity.status(HttpStatus.CREATED).body(systemUserService.save(systemUser));
//
//    }
////    @PreAuthorize("hasAnyRole('DIRECTOR', 'ADMIN')")
//    @GetMapping("/{id}")
//    public ResponseEntity<Object> read(@PathVariable(value = "id") UUID id) {
//        Optional<SystemUser> systemUserOptional = systemUserService.findById(id);
//        log.info("System User searched by id");
//        if(!systemUserOptional.isPresent()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("System User not found");
//        }
//        log.info("System User found by id");
//        return ResponseEntity.status(HttpStatus.OK).body(systemUserOptional.get());
//    }
////    @PreAuthorize("hasAnyRole('DIRECTOR', 'ADMIN')")
//    @PutMapping("/{id}")
//    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
//                                         @RequestBody SystemUserDto systemUserDto) {
//        Optional<SystemUser> systemUserOptional = systemUserService.findById(id);
//        if(!systemUserOptional.isPresent()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("System User not found");
//        }
//        SystemUser systemUser = new SystemUser();
//        BeanUtils.copyProperties(systemUserDto, systemUser);
//        systemUser.setUserId(systemUserOptional.get().getUserId());
//        systemUser.setRegistrationDate(systemUserOptional.get().getRegistrationDate);
//        return ResponseEntity.status(HttpStatus.OK).body(systemUserService.save(systemUser));
//    }
////    @PreAuthorize("hasAnyRole('DIRECTOR')")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
//        Optional<SystemUser> systemUserOptional = systemUserService.findById(id);
//        log.info("System User searched by id");
//        if(!systemUserOptional.isPresent()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("System User not found");
//        }
//        log.info("System User found by id");
//        systemUserService.delete((systemUserOptional.get()));
//        return ResponseEntity.status(HttpStatus.OK).body("System User deleted successfully");
//    }
////    @PreAuthorize("hasAnyRole('DIRECTOR', 'ADMIN')")
//    @GetMapping()
//    public ResponseEntity<Page<SystemUser>> list(@PageableDefault(page = 0, size = 5, sort = "id",
//            direction = Sort.Direction.ASC) Pageable pageable) {
//        log.info("System User's list showed");
//        return ResponseEntity.status(HttpStatus.OK).body(systemUserService.findAll(pageable));
//    }
//}
