package com.novopacksv.production.dto.roll;

import com.novopacksv.production.dto.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;
import com.novopacksv.production.model.productModel.ProductType;

@Getter
@Setter
public class RollOperationResponseWithProduct extends BaseEntityResponse {

    private String operationDate;

    private String operationType;

    private String manufacturedDate;

    private Long rollTypeId;

    private Integer rollAmount;

    private ProductType productType;
}
