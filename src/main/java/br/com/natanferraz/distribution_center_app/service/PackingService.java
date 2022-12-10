package br.com.natanferraz.distribution_center_app.service;

import br.com.natanferraz.distribution_center_app.model.Invoice;
import br.com.natanferraz.distribution_center_app.model.Packing;
import br.com.natanferraz.distribution_center_app.repository.PackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class PackingService {
    @Autowired
    PackingRepository packingRepository;
    @Transactional
    public Packing save(Packing packing){
        return packingRepository.save(packing);
    }
    @Transactional
    public void delete(Packing packing){
        packingRepository.delete(packing);
    }

    public Page<Packing> findAll(Pageable pageable) {
        return packingRepository.findAll(pageable);
    }
    public Optional<Packing> findById(UUID id) {
        return packingRepository.findById(id);
    }

    public boolean existsByDescription(String description) {
        return packingRepository.existsByDescription(description);
    }
}
