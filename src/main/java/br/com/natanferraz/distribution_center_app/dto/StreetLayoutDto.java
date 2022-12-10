package br.com.natanferraz.distribution_center_app.dto;

import br.com.natanferraz.distribution_center_app.model.Pallet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class StreetLayoutDto {
    private String street;
    private String picking;
    private int level;
    private Pallet pallet;
}
