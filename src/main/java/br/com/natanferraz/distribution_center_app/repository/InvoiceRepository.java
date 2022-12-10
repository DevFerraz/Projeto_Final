package br.com.natanferraz.distribution_center_app.repository;

import br.com.natanferraz.distribution_center_app.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    boolean existsByInvoiceNumber(String invoiceNumber);
}
