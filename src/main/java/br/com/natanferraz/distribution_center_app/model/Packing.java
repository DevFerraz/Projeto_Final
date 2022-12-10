package br.com.natanferraz.distribution_center_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
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

    @Column(nullable = false, length = 50)
    private String description;

    @OneToMany(mappedBy = "packing", cascade = CascadeType.ALL)
    private List<Product> products;

    public Packing(){
        this.registrationDate = LocalDateTime.now(ZoneId.of("UTC"));
    }
}