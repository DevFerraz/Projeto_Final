package br.com.natanferraz.distribution_center_app.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
public class ProductDto {
    private String description;
    private String packageProduct;
    private double weight;
    private Date expirationDate;
    private Date fabricationDate;
    private String batch;
    private int quantity;
}
