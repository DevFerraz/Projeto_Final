package br.com.natanferraz.distribution_center_app.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Packing implements Serializable {
    @Serial
    private static final long serialVersionUID = -2L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    public LocalDateTime registrationDate;

    @Column(nullable = false, length = 50, unique = true)
    private String description;

    @OneToMany(mappedBy = "packing")
    private List<Product> products;

    public Packing(){
        this.registrationDate = LocalDateTime.now(ZoneId.of("UTC"));
    }

    public void setDescription(String description) {
        this.description = description.toUpperCase();
    }
}