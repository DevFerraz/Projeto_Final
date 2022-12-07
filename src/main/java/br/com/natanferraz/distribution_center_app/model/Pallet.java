package br.com.natanferraz.distribution_center_app.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Pallet implements Serializable {
    @Serial
    private static final long serialVersionUID = -3L;
    public LocalDateTime getRegistrationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 10)
    private double maxWeight;
    @Column(nullable = false, length = 10)
    private String status;
    @Column(nullable = false, length = 5)
    private double length;
    @Column(nullable = false, length = 5)
    private double width;
    @Column(nullable = false, length = 5)
    private double height;
    @Column(nullable = false, length = 5)
    private double weight;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "street_layout_id")
    private StreetLayout streetLayout;

    public Pallet(double maxWeight, String status,
                  double length, double width, double height,
                  double weight) {
        this.maxWeight = maxWeight;
        this.status = status;
        this.height = height;
        this.weight = weight;
        this.length = length;
        this.width = width;
    }

    @Deprecated
    public Pallet() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public StreetLayout getStreetLayout() {
        return streetLayout;
    }

    public void setStreetLayout(StreetLayout streetLayout) {
        this.streetLayout = streetLayout;
    }

    public void setRegistrationDate(LocalDateTime utc) {
    }
}

