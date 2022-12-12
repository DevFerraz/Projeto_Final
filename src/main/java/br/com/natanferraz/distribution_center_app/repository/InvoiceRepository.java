package br.com.natanferraz.distribution_center_app.repository;

import br.com.natanferraz.distribution_center_app.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    boolean existsByInvoiceNumber(String invoiceNumber);
}
