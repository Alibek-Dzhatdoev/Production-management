package com.novopacksv.production.dto.roll;

import com.novopacksv.production.dto.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RollOperationResponse extends BaseEntityResponse {

    private String operationDate;

    private String operationType;

    private String manufacturedDate;

    private Long rollTypeId;

    private Integer rollAmount;

    private Long productTypeIdForUseOperation;

}
