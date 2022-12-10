package br.com.natanferraz.distribution_center_app.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Packing implements Serializable {
    @Serial
    private static final long serialVersionUID = -2L;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    public LocalDateTime registrationDate;

    @Column(nullable = false, length = 50)
    private String description;

    @OneToMany(mappedBy = "packing", cascade = CascadeType.ALL)
    private Collection<Product> product;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}