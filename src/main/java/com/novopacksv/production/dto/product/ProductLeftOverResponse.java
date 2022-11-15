package com.novopacksv.production.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductLeftOverResponse {

    private String date;

    private Long productTypeId;

    private Integer amount;

}
