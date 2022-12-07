//package br.com.natanferraz.distribution_center_app.service;
//
//import br.com.natanferraz.distribution_center_app.model.SystemUser;
//import br.com.natanferraz.distribution_center_app.repository.SystemUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//import java.util.UUID;
//@Service
//public class SystemUserService {
//    @Autowired
//    SystemUserRepository systemUserRepository;
//    @Transactional
//    public SystemUser save(SystemUser systemUser){
//        return systemUserRepository.save(systemUser);
//    }
//    @Transactional
//    public void delete(SystemUser systemUser){
//        systemUserRepository.delete(systemUser);
//    }
//    public Page<SystemUser> findAll(Pageable pageable) {
//        return systemUserRepository.findAll(pageable);
//    }
//    public Optional<SystemUser> findById(UUID id) {
//        return systemUserRepository.findById(id);
//    }
//}

