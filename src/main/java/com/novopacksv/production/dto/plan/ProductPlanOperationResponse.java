package com.novopacksv.production.dto.plan;

import com.novopacksv.production.dto.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPlanOperationResponse extends BaseEntityResponse {

    private String date;

    private Long productTypeId;

    private Long rollTypeId;

    private Integer rollAmount;

    private Integer productAmount;

    private Integer rollToMachinePlane;

}
