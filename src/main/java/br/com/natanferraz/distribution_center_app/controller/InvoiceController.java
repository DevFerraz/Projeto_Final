package br.com.natanferraz.distribution_center_app.controller;

import br.com.natanferraz.distribution_center_app.dto.InvoiceDto;
import br.com.natanferraz.distribution_center_app.model.CustomError;
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
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/invoice", produces = "application/json")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
//    @PreAuthorize("hasAnyRole('DIRECTOR', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody InvoiceDto invoiceDto) {
        if(invoiceService.existsByInvoiceNumber(invoiceDto.getInvoiceNumber())){

            CustomError error = new CustomError( HttpStatus.CONFLICT,
                    "Conflict: Invoice already created");
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(error);
        }
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceDto, invoice);
        log.info("Invoice created");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(invoiceService
                        .save(invoice));
    }
//    @PreAuthorize("hasAnyRole('EMPLOYEE', 'DIRECTOR', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@PathVariable(value = "id") UUID id) {
        Optional<Invoice> invoiceOptional = invoiceService.findById(id);
        log.info("Invoice searched by id");
        if(invoiceOptional.isEmpty()){
            CustomError error = new CustomError( HttpStatus.NOT_FOUND,
                    "Not Found: Invoice not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        log.info("Invoice found by id");
        return ResponseEntity.status(HttpStatus.OK).body(invoiceOptional.get());
    }
//    @PreAuthorize("hasAnyRole('DIRECTOR', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                         @RequestBody InvoiceDto invoiceDto) {
        Optional<Invoice> invoiceOptional = invoiceService.findById(id);
        if(invoiceOptional.isEmpty()){

            CustomError error = new CustomError( HttpStatus.NOT_FOUND,
                    "Not Found: Invoice not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceDto, invoice);
        invoice.setId(invoiceOptional.get().getId());
        invoice.setRegistrationDate(invoiceOptional.get().registrationDate);
        return ResponseEntity.status(HttpStatus.OK).body(invoiceService.save(invoice));
    }
//    @PreAuthorize("hasAnyRole('DIRECTOR', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        Optional<Invoice> invoiceOptional = invoiceService.findById(id);
        log.info("Invoice searched by id");
        if(invoiceOptional.isEmpty()){

            CustomError error = new CustomError(HttpStatus.NOT_FOUND,
                    "Not Found: Invoice not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
        log.info("Invoice found by id");
        invoiceService.delete((invoiceOptional.get()));
        CustomError error = new CustomError( HttpStatus.NO_CONTENT,
                "Invoice deleted successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(error);
    }
//    @PreAuthorize("hasAnyRole('EMPLOYEE', 'DIRECTOR', 'ADMIN')")
    @GetMapping()
    public ResponseEntity<Page<Invoice>> list(@PageableDefault(size = 5, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Invoice list showed");
        return ResponseEntity.status(HttpStatus.OK).body(invoiceService.findAll(pageable));
    }
}