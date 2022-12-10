package br.com.natanferraz.distribution_center_app.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private Boolean enabled;
    private String authority;
    private String name;
    private String phoneNumber;
    private double salary;

    public String getUsername(){
        return this.username;
    }

}
