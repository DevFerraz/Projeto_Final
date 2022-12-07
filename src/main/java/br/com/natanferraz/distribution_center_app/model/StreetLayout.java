package br.com.natanferraz.distribution_center_app.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
public class StreetLayout implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    private String picking;
    private int level;

    @OneToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)

    private Pallet pallet;

    public Pallet getPallet() {
        return pallet;
    }

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }

    public StreetLayout(String street, String picking, int level) {
        this.street = street;
        this.picking = picking;
        this.level = level;
    }

    @Deprecated
    public StreetLayout(){

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String rua) {
        this.street = rua;
    }

    public String getPicking() {
        return picking;
    }

    public void setPicking(String picking) {
        this.picking = picking;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int nivel) {
        this.level = nivel;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}