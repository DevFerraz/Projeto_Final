package br.com.natanferraz.distribution_center_app.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class StreetLayout implements Serializable {
    @Serial
    private static final long serialVersionUID = -5L;
    public LocalDateTime getRegistrationDate;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 10)
    private String street;
    @Column(nullable = false, unique = true, length = 5)
    private String picking;
    @Column(nullable = false, length = 2)
    private int level;
    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pallet_id")
    private Pallet pallet;

    public Pallet getPallet() {
        return pallet;
    }

    public void setPallet(Pallet pallet) {
        this.pallet = pallet;
    }

    public StreetLayout(String street, String picking, int level, Pallet pallet) {
        this.street = street;
        this.picking = picking;
        this.level = level;
        this.pallet = pallet;
    }

    @Deprecated
    public StreetLayout(){

    }

    public void setRegistrationDate(LocalDateTime utc) {
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


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}