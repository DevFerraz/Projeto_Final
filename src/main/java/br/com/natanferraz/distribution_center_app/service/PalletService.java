package br.com.natanferraz.distribution_center_app.service;

import br.com.natanferraz.distribution_center_app.model.Pallet;
import br.com.natanferraz.distribution_center_app.repository.PalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class PalletService {
    @Autowired
    PalletRepository palletRepository;
    @Transactional
    public Pallet save(Pallet pallet){
        return palletRepository.save(pallet);
    }
    @Transactional
    public void delete(Pallet pallet){
        palletRepository.delete(pallet);
    }
    public Page<Pallet> findAll(Pageable pageable) {
        return palletRepository.findAll(pageable);
    }
    public Optional<Pallet> findById(UUID id) {
        return palletRepository.findById(id);
    }
    public boolean existsByHeightAndAndWeightAndLengthAndWidth(double height, double weight,
                                     double length, double width){
        return palletRepository.existsByHeightAndAndWeightAndLengthAndWidth(height, weight, length, width);
    }

}
