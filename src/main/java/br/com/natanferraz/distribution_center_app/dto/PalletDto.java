package br.com.natanferraz.distribution_center_app.dto;

import br.com.natanferraz.distribution_center_app.model.StreetLayout;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PalletDto {

    private StreetLayout streetLayout;
    private double maxWeight;
    private String status;
    private double length;
    private double width;
    private double height;
    private double weight;

}
