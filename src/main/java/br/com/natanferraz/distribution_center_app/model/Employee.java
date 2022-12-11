package br.com.natanferraz.distribution_center_app.model;

import br.com.natanferraz.distribution_center_app.enums.Authority;
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
public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = -8L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Authority authority;

    public LocalDateTime registrationDate;

    public Employee(){
        this.registrationDate = LocalDateTime.now(ZoneId.of("UTC"));
    }
}
