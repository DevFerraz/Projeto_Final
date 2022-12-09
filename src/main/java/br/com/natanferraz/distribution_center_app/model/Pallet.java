package br.com.natanferraz.distribution_center_app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Pallet implements Serializable {
    @Serial
    private static final long serialVersionUID = -3L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    public LocalDateTime registrationDate;

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
}

