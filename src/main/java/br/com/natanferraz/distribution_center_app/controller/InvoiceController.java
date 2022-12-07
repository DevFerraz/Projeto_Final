package br.com.natanferraz.distribution_center_app.controller;

import br.com.natanferraz.distribution_center_app.dto.InvoiceDto;
import br.com.natanferraz.distribution_center_app.model.Invoice;
import br.com.natanferraz.distribution_center_app.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
//    @PreAuthorize("hasAnyRole('DIRECTOR', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody InvoiceDto invoiceDto) {
        if(invoiceService.existsByInvoiceNumber(invoiceDto.getInvoiceNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflict: Invoice already created");
        }
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceDto, invoice);
        invoice.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        log.info("Dimension created");
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.save(invoice));

    }
//    @PreAuthorize("hasAnyRole('EMPLOYEE', 'DIRECTOR', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@PathVariable(value = "id") UUID id) {
        Optional<Invoice> invoiceOptional = invoiceService.findById(id);
        log.info("Invoice searched by id");
        if(!invoiceOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice not found");
        }
        log.info("Invoice found by id");
        return ResponseEntity.status(HttpStatus.OK).body(invoiceOptional.get());
    }
//    @PreAuthorize("hasAnyRole('DIRECTOR', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                         @RequestBody InvoiceDto invoiceDto) {
        Optional<Invoice> invoiceOptional = invoiceService.findById(id);
        if(!invoiceOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice not found");
        }
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceDto, invoice);
        invoice.setId(invoiceOptional.get().getId());
        invoice.setRegistrationDate(invoiceOptional.get().getRegistrationDate);
        return ResponseEntity.status(HttpStatus.OK).body(invoiceService.save(invoice));
    }
//    @PreAuthorize("hasAnyRole('DIRECTOR', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        Optional<Invoice> invoiceOptional = invoiceService.findById(id);
        log.info("Invoice searched by id");
        if(!invoiceOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice not found");
        }
        log.info("Invoice found by id");
        invoiceService.delete((invoiceOptional.get()));
        return ResponseEntity.status(HttpStatus.OK).body("Invoice deleted successfully");
    }
//    @PreAuthorize("hasAnyRole('EMPLOYEE', 'DIRECTOR', 'ADMIN')")
    @GetMapping()
    public ResponseEntity<Page<Invoice>> list(@PageableDefault(size = 5, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Invoice list showed");
        return ResponseEntity.status(HttpStatus.OK).body(invoiceService.findAll(pageable));
    }
}