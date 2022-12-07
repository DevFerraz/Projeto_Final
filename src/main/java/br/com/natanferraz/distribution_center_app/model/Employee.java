package br.com.natanferraz.distribution_center_app.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="employee")
public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = -8L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 30)
    private String name;
    @Column(nullable = false, unique = true, length = 15)
    private String phoneNumber;
    @Column(nullable = false, length = 9)
    private double salary;
    @Column(nullable = false, length = 40)
    private String role;
    public LocalDateTime getRegistrationDate;
}
