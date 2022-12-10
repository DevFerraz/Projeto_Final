package br.com.natanferraz.distribution_center_app.service;

import br.com.natanferraz.distribution_center_app.model.User;
import br.com.natanferraz.distribution_center_app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Transactional
    public User save(User user){
        User savedUser = userRepository.save(user);
        log.info("Invoice created");
        return savedUser;
    }
    @Transactional
    public void delete(User user){
        userRepository.delete(user);
        log.info("User deleted", user);
    }
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsById(username);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

