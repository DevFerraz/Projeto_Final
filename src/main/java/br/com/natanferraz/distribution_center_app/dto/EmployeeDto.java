package br.com.natanferraz.distribution_center_app.dto;

import br.com.natanferraz.distribution_center_app.enums.Authority;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EmployeeDto {
    private String name;
    private String phoneNumber;
    private double salary;
    private Authority authority;
}
