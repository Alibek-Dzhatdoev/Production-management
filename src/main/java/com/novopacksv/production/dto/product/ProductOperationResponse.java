package com.novopacksv.production.dto.product;

import com.novopacksv.production.dto.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOperationResponse extends BaseEntityResponse {

    private String operationDate;

    private Long productTypeId;

    private String operationType;

    private Integer amount;
}
