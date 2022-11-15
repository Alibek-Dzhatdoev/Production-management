package com.novopacksv.production.dto.product;

import com.novopacksv.production.dto.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductTypeResponse extends BaseEntityResponse {

    private String name;

    private Double weight;

    private String colorCode;

}
