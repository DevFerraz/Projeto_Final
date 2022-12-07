package br.com.natanferraz.distribution_center_app.model;


import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
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
    public LocalDateTime getRegistrationDate;
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
    @Deprecated
    public Product(){
    }

    public void setRegistrationDate(LocalDateTime utc) {
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackageProduct() {
        return packageProduct;
    }

    public void setPackageProduct(String packageProduct) {
        this.packageProduct = packageProduct;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Date getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(Date fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public void setExpirationDate(Date date) {
        this.expirationDate = date;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Packing getPacking() {
        return packing;
    }

    public void setPacking(Packing packing) {
        this.packing = packing;
    }
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}