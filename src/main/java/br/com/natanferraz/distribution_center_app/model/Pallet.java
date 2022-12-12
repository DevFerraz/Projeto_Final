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
public class Pallet implements Serializable {
    @Serial
    private static final long serialVersionUID = -5L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    public LocalDateTime registrationDate;
    @Column(nullable = false)
    private double maxWeight;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private double length;
    @Column(nullable = false)
    private double width;
    @Column(nullable = false)
    private double height;
    @Column(nullable = false)
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
        this.registrationDate = LocalDateTime.now(ZoneId.of("UTC"));
    }
    public Pallet(){
        this.registrationDate = LocalDateTime.now(ZoneId.of("UTC"));
    }
}

