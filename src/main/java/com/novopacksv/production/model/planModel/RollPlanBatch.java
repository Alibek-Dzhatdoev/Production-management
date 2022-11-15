package com.novopacksv.production.model.planModel;

import com.novopacksv.production.model.rollModel.RollType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class RollPlanBatch {

    private LocalDate date;

    private RollType rollType;

    private Integer rollPlanManufacturedAmount;

    private Integer rollPlanUsedAmount;
}
