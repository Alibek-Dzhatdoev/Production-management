package com.novopacksv.production.dto.roll;

import com.novopacksv.production.dto.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RollTypeResponse extends BaseEntityResponse {

    private String note;

    private Double thickness;

    private Double minWeight;

    private Double maxWeight;

    private Double length;

    private String colorCode;

}