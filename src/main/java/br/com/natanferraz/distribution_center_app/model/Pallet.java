package br.com.natanferraz.distribution_center_app.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
public class Pallet implements Serializable {
    @Serial
    private static final long serialVersionUID = -3L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double maxWeight;
    private String status;
    private double length;
    private double width;
    private double height;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}

