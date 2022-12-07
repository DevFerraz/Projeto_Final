package br.com.natanferraz.distribution_center_app.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class StreetLayoutDto {
    private String street;
    private String picking;
    private int level;
}
