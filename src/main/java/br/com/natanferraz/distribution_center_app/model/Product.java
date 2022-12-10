package br.com.natanferraz.distribution_center_app.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = -4L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 50)
    private String description;
    @Column(nullable = false, length = 50)
    private String packageProduct;
    @Column(nullable = false, length = 5)
    private double weight;
    @Column(nullable = false)
    private Date expirationDate;
    @Column(nullable = false)
    private Date fabricationDate;
    @Column(nullable = false, length = 5)
    private double length;
    @Column(nullable = false, length = 5)
    private double width;
    @Column(nullable = false, length = 5)
    private double height;
    @Column(nullable = false, length = 50)
    private String batch;
    @Column(nullable = false, length = 5)
    private int quantity;
    public LocalDateTime registrationDate;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Packing packing;

    public Product(String packageProduct, double weight, Date expirationDate,
                   Date fabricationDate, double length, double width,
                   double height, String batch,
                   int quantity, Packing packing) {
        this.packageProduct = packageProduct;
        this.weight = weight;
        this.expirationDate = expirationDate;
        this.fabricationDate = fabricationDate;
        this.height = height;
        this.batch = batch;
        this.quantity = quantity;
        this.packing = packing;
        this.length = length;
        this.width = width;
    }
}