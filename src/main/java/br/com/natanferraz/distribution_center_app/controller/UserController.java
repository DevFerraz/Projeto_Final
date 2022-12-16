package br.com.natanferraz.distribution_center_app.controller;

import br.com.natanferraz.distribution_center_app.dto.UserDto;
import br.com.natanferraz.distribution_center_app.model.CustomError;
import br.com.natanferraz.distribution_center_app.model.User;
import br.com.natanferraz.distribution_center_app.service.UserService;
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


@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/user", produces = "application/json")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value="/create", produces = "application/json")
    public ResponseEntity<Object> create(@RequestBody UserDto userDto) {
        if (userService.existsByUsername(userDto.getUsername())) {
            CustomError error = new CustomError( HttpStatus.CONFLICT,
                    "Conflict: Username already created");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(error);
        }else if (userService.findByName(userDto.getName()).isPresent()) {
            CustomError error = new CustomError( HttpStatus.CONFLICT,
                    "Conflict: User already created");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(error);
        }else if (userService.findByPhoneNumber(userDto.getPhoneNumber()).isPresent()) {
            CustomError error = new CustomError( HttpStatus.CONFLICT,
                    "Conflict: Phone Number already created");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(error);
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService
                        .save(user));
    }
    @GetMapping(value="/{username}", produces = "application/json")
    public ResponseEntity<Object> read(@PathVariable(value = "username") String username) {
        Optional<User> userOptional = userService.findByUsername(username);
        log.info("User searched by id");
        if (userOptional.isEmpty()) {
            CustomError error = new CustomError( HttpStatus.NOT_FOUND,
                    "Not Found: User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        log.info("User found by id");
        return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
    }

    @PutMapping(value="/{username}", produces = "application/json")
    public ResponseEntity<Object> update(@PathVariable(value = "username") String username,
                                         @RequestBody UserDto userDto) {
        userDto.setUsername(username);
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            CustomError error = new CustomError( HttpStatus.NOT_FOUND,
                    "Not Found: User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setRegistrationDate(userOptional.get().registrationDate);
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }

    @DeleteMapping(value="/{username}", produces = "application/json")
    public ResponseEntity<Object> delete(@PathVariable(value = "username")String username) {
        Optional<User> userOptional = userService.findByUsername(username);
        log.info("User searched by id");
        if (userOptional.isEmpty()) {
            CustomError error = new CustomError( HttpStatus.NOT_FOUND,
                    "Not Found: User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);        }
        log.info("User found by id");
        userService.delete((userOptional.get()));
        CustomError error = new CustomError( HttpStatus.NO_CONTENT,
                "User deleted successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(error);
    }

    @GetMapping()
    public ResponseEntity<Page<User>> list(@PageableDefault(size = 5, sort = "registrationDate",
            direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("User list showed");
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }
}
