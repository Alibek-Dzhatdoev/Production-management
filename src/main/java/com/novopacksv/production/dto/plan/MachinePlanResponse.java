package com.novopacksv.production.dto.plan;

import com.novopacksv.production.dto.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MachinePlanResponse extends BaseEntityResponse {

    private Integer machineNumber;

    private String timeStart;

    private Long productTypeId;

    private Integer productAmount;

    private Double duration;

    private Boolean isImportant;

}
