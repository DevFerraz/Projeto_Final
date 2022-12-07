package br.com.natanferraz.distribution_center_app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Invoice implements Serializable {
    @Serial
    private static final long serialVersionUID = -2L;
    public LocalDateTime getRegistrationDate;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 10)
    private String invoiceNumber;
    @Column(nullable = false)
    private Date expeditionDate;
    @Column(nullable = false, length = 10)
    private double invoiceValue;
    @Column(nullable = false, unique = true, length = 15)
    private String providerRegister;
    @Column(nullable = false, unique = true, length = 15)
    private String providerPhone;
    @Column(nullable = false, unique = true, length = 40)
    private String providerAddress;
    @Column(nullable = false, length = 20)
    private String product;
    private int quantity;

    public void setRegistrationDate(LocalDateTime utc) {
    }
}
