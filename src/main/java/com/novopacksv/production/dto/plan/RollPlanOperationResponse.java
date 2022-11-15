package com.novopacksv.production.dto.plan;

import com.novopacksv.production.dto.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RollPlanOperationResponse extends BaseEntityResponse {

    private String date;

    private Long rollTypeId;

    private Integer rollAmount;
}
