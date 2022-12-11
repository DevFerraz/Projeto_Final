package br.com.natanferraz.distribution_center_app.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@Entity
public class Invoice implements Serializable {
    @Serial
    private static final long serialVersionUID = -2L;
    public LocalDateTime registrationDate;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 9)
    private String invoiceNumber;

    @Column(nullable = false)
    private Date expeditionDate;

    @Column(nullable = false)
    private Double invoiceValue;

    @Column(nullable = false, unique = true, length = 15)
    private String providerRegister;

    @Column(nullable = false, length = 17)
    private String providerPhone;

    @Column(nullable = false)
    private String providerAddress;

    @Column(nullable = false)
    private String product;

    private int quantity;

    public Invoice(){
        this.registrationDate = LocalDateTime.now(ZoneId.of("UTC"));
    }

    public Invoice(String invoiceNumber, Date expeditionDate, Double invoiceValue,
                   String providerRegister, String providerPhone, String providerAddress, String product, int quantity) {

        this.registrationDate = LocalDateTime.now(ZoneId.of("UTC"));
        this.invoiceNumber = invoiceNumber;
        this.expeditionDate = expeditionDate;
        this.invoiceValue = invoiceValue;
        this.providerRegister = providerRegister;
        this.providerPhone = providerPhone;
        this.providerAddress = providerAddress;
        this.product = product;
        this.quantity = quantity;
    }
}
