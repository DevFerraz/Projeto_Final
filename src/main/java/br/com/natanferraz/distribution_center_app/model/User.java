package br.com.natanferraz.distribution_center_app.model;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.text.DecimalFormat;

@Entity
@Getter
@Setter
@Table(name="users")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -8L;
    @Id
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private Boolean enabled = true;

    @Column(nullable = false)
    private String authority;

    public LocalDateTime registrationDate;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, length = 17)
    private String phoneNumber;

    @Column(nullable = false)
    private Double salary;

    public User(){
        this.registrationDate = LocalDateTime.now(ZoneId.of("UTC"));
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
