package br.com.natanferraz.distribution_center_app.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
public class Dimension implements Serializable {
    @Serial
    private static final long serialVersionUID = -2L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double length;
    private double width;
    private double height;
    private double weight;
    @Deprecated
    public Dimension() {
    }

    public Dimension(double weight, double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.weight = weight;
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}


