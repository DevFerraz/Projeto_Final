package br.com.natanferraz.distribution_center_app.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@Getter
@Setter
public class StreetLayout implements Serializable {
    @Serial
    private static final long serialVersionUID = -7L;
    public LocalDateTime registrationDate;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String picking;
    @Column(nullable = false, length = 2)
    private int level;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pallet_id")
    private Pallet pallet;
    public StreetLayout(String street, String picking, int level, Pallet pallet) {
        this.street = street;
        this.picking = picking;
        this.level = level;
        this.pallet = pallet;
        this.registrationDate = LocalDateTime.now(ZoneId.of("UTC"));
    }
    public StreetLayout(){
        this.registrationDate = LocalDateTime.now(ZoneId.of("UTC"));
    }
}