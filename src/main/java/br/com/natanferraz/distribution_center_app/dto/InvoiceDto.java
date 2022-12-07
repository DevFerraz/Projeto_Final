package br.com.natanferraz.distribution_center_app.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class InvoiceDto {
    private String invoiceNumber;
    private Date expeditionDate;
    private double invoiceValue;
    private String providerRegister;
    private String providerPhone;
    private String providerAddress;
    private String product;
    private int quantity;
}
