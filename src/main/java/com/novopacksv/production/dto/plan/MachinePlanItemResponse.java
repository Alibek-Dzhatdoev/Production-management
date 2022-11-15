package com.novopacksv.production.dto.plan;

import com.novopacksv.production.dto.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MachinePlanItemResponse extends BaseEntityResponse {

    private Long rollTypeId;

    private Integer rollAmount;

    private Integer productAmount;

    private Long machinePlanId;

}
