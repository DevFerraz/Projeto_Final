package br.com.natanferraz.distribution_center_app.service;

import br.com.natanferraz.distribution_center_app.model.UserModel;
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
    public UserModel save(UserModel userModel){
        UserModel savedUserModel = userRepository.save(userModel);
        log.info("Invoice created");
        return savedUserModel;
    }
    @Transactional
    public void delete(UserModel userModel){
        userRepository.delete(userModel);
        log.info("User deleted", userModel);
    }
    public Page<UserModel> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsById(username);
    }

    public Optional<UserModel> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

