package br.com.natanferraz.distribution_center_app.controller;

import br.com.natanferraz.distribution_center_app.config.WebSecurityConfig;
import br.com.natanferraz.distribution_center_app.dto.UserDto;
import br.com.natanferraz.distribution_center_app.model.UserModel;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    DataSource dataSource;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody UserDto userDto) {
        UserModel userModel = new UserModel();
        UserDetails newUser = User.builder()
                .username(userDto.getUsername())
                .password(WebSecurityConfig.passwordEncoder().encode(userDto.getPassword()))
                .authorities(userDto.getAuthority().toString())
                .build();

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        log.info("[CUSTOM]Getting users from database", users);
        if(!users.userExists(newUser.getUsername())){
            users.createUser(newUser);
            userDto.setPassword(null);
            userDto.setAuthority(null);


            BeanUtils.copyProperties(userDto, userModel);
            userModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
            log.info("User created");
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@PathVariable(value = "id") String username) {
        Optional<UserModel> userOptional = userService.findByUsername(username);
        log.info("User searched by id");
        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        log.info("User found by id");
        return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") String username,
                                         @RequestBody UserDto userDto) {
        Optional<UserModel> userOptional = userService.findByUsername(username);
        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUsername(userOptional.get().getUsername());
        userModel.setRegistrationDate(userOptional.get().registrationDate);
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") String username) {
        Optional<UserModel> userOptional = userService.findByUsername(username);
        log.info("User searched by id");
        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        log.info("User found by id");
        userService.delete((userOptional.get()));
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }
    @GetMapping()
    public ResponseEntity<Page<UserModel>> list(@PageableDefault(page = 0, size = 5, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("User's list showed");
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }
}
