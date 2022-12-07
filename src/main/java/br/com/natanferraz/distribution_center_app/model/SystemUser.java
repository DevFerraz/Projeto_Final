//package br.com.natanferraz.distribution_center_app.model;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.io.Serial;
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import java.util.Collection;
//import java.util.UUID;
//
//@Entity
//@Table(name="users")
//public class SystemUser implements UserDetails, Serializable {
//    @Serial
//    private static final long serialVersionUID = -7L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID userId;
//    @Column(nullable = false, unique = true, length = 50)
//    private String username;
//    @Column(nullable = false, length = 50)
//    private String password;
//    @Column(nullable = false)
//    private Boolean enabled;
//
//    @Column(nullable = false)
//    private String authority;
//    public LocalDateTime getRegistrationDate;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    public UUID getUserId() {
//        return userId;
//    }
//
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Boolean getEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(Boolean enabled) {
//        this.enabled = enabled;
//    }
//
//    public LocalDateTime getGetRegistrationDate() {
//        return getRegistrationDate;
//    }
//
//    public void setGetRegistrationDate(LocalDateTime getRegistrationDate) {
//        this.getRegistrationDate = getRegistrationDate;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    public void setUserId(UUID userId) {
//        this.userId = userId;
//    }
//
//    public String getAuthority() {
//        return authority;
//    }
//
//    public void setAuthority(String authority) {
//        this.authority = authority;
//    }
//
//
//    @Override
//    public boolean isEnabled() {
//        return this.enabled;
//    }
//
//    public void setRegistrationDate(LocalDateTime utc) {
//    }
//}
