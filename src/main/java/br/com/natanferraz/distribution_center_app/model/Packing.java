package br.com.natanferraz.distribution_center_app.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Packing implements Serializable {
    @Serial
    private static final long serialVersionUID = -2L;
    public LocalDateTime getRegistrationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 50)
    private String description;

    @OneToMany(mappedBy = "packing", cascade = CascadeType.ALL)
    private Collection<Product> product;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRegistrationDate(LocalDateTime utc) {
    }
}