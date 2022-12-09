package br.com.natanferraz.distribution_center_app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    public LocalDateTime registrationDate;

    @Column(nullable = false, length = 50)
    private String description;

    @OneToMany(mappedBy = "packing", cascade = CascadeType.ALL)
    private Collection<Product> product;
}