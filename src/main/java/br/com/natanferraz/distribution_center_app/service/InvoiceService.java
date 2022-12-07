package br.com.natanferraz.distribution_center_app.service;

import br.com.natanferraz.distribution_center_app.model.Invoice;
import br.com.natanferraz.distribution_center_app.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Transactional
    public Invoice save(Invoice invoice){
        return invoiceRepository.save(invoice);
    }
    @Transactional
    public void delete(Invoice invoice){
        invoiceRepository.delete(invoice);
    }
    public Page<Invoice> findAll(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }
    public Optional<Invoice> findById(UUID id) {
        return invoiceRepository.findById(id);
    }
    public boolean existsByInvoiceNumber(String invoiceNumber) {
        return invoiceRepository.existsByInvoiceNumber(invoiceNumber);
    }
}
