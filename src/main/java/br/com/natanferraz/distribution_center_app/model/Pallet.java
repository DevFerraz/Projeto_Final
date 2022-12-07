package br.com.natanferraz.distribution_center_app.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
public class Pallet implements Serializable {
    @Serial
    private static final long serialVersionUID = -4L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double maxWeight;
    private String status;

    @ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    private Dimension dimension;

    @OneToOne(mappedBy="pallet", cascade=CascadeType.PERSIST)
    private StreetLayout streetLayout;
    public Pallet(double maxWeight, String status,
                  Dimension dimension){
        this.maxWeight = maxWeight;
        this.status = status;
        this.dimension = dimension;
    }
    @Deprecated
    public Pallet() {
    }

    public StreetLayout getArruamento() {
        return streetLayout;
    }

    public void setArruamento(StreetLayout streetLayout) {
        this.streetLayout = streetLayout;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double pesoMaximo) {
        this.maxWeight = pesoMaximo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
}

