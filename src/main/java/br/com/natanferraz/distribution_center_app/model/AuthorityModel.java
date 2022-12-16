package br.com.natanferraz.distribution_center_app.model;

import br.com.natanferraz.distribution_center_app.enums.Authority;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "authorities")
public class AuthorityModel implements GrantedAuthority, Serializable {
    @Serial
    private static final long serialVersionUID = -1L;
    @Id
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Authority authority;
    public LocalDateTime getRegistrationDate;
    @ManyToMany
    @JoinTable(name = "users_authority", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "authorithy_id"))
    private List<AuthorityModel> authorities;

    public Collection<? extends GrantedAuthority> getAutorities(){
        return this.authorities;
    }

    @Override
    public String getAuthority(){
        return this.authority.toString();
    }
}
