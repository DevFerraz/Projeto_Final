package br.com.natanferraz.distribution_center_app.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = -6L;
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
    private Integer quantity;
    public LocalDateTime registrationDate;
    public LocalDateTime lastUpdateDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Packing packing;

//    private Double costPrice;
//    private Double sellPrice;

    public Product(String packageProduct, double weight, Date expirationDate,
                   Date fabricationDate, double length, double width,
                   double height, String batch,
                   int quantity, Packing packing) {
        this.registrationDate = LocalDateTime.now(ZoneId.of("UTC"));
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

    public Product(){
        this.registrationDate = LocalDateTime.now(ZoneId.of("UTC"));
    }

    public void increaseQuantity(Integer qty) {
        this.quantity += qty;
    }

    public void decreaseQuantity(Integer qty) {
        this.quantity -= (qty);
    }
}