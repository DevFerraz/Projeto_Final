package br.com.natanferraz.distribution_center_app.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SystemUserDto {
    private String username;
    private String password;
    private Boolean enabled;
}
