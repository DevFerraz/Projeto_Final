package br.com.natanferraz.distribution_center_app.dto;

import br.com.natanferraz.distribution_center_app.model.Packing;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
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
    private double length;
    private double width;
    private double height;
    private Packing packing;
}
