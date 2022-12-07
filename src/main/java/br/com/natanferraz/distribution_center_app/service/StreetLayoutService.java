package br.com.natanferraz.distribution_center_app.service;

import br.com.natanferraz.distribution_center_app.model.StreetLayout;
import br.com.natanferraz.distribution_center_app.repository.StreetLayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class StreetLayoutService {
    @Autowired
    StreetLayoutRepository streetLayoutRepository;
    @Transactional
    public StreetLayout save(StreetLayout streetLayout){
        return streetLayoutRepository.save(streetLayout);
    }
    @Transactional
    public void delete(StreetLayout streetLayout){
        streetLayoutRepository.delete(streetLayout);
    }
    public boolean existsByStreet(String street){
        return streetLayoutRepository.existsByStreet(street);
    }
    public Page<StreetLayout> findAll(Pageable pageable) {
        return streetLayoutRepository.findAll(pageable);
    }
    public Optional<StreetLayout> findById(UUID id) {
        return streetLayoutRepository.findById(id);
    }
}


