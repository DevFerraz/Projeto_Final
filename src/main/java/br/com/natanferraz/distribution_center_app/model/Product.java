package br.com.natanferraz.distribution_center_app.model;


import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = -5L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String packageProduct;
    private double weight;
    private Date expirationDate;
    private Date fabricationDate;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    private Dimension dimension;
    private String batch;
    private int quantity;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    private Packing packing;

    public Product(String packageProduct, double weight, Date expirationDate,
                   Date fabricationDate, Dimension dimension, String batch,
                   int quantity, Packing packing) {
        this.packageProduct = packageProduct;
        this.weight = weight;
        this.expirationDate = expirationDate;
        this.fabricationDate = fabricationDate;
        this.dimension = dimension;
        this.batch = batch;
        this.quantity = quantity;
        this.packing = packing;
    }
    @Deprecated
    public Product(){
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

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}