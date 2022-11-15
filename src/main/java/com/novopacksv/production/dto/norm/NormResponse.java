package com.novopacksv.production.dto.norm;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NormResponse {

    private List<Long> rollTypeIds;

    private Long productTypeId;

    private Integer norm;

    private Integer normForDay;
}
