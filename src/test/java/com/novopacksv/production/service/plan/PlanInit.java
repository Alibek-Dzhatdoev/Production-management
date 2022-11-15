package com.novopacksv.production.service.plan;

import com.novopacksv.production.model.planModel.RollPlanOperation;
import com.novopacksv.production.model.rollModel.RollType;

import java.time.LocalDate;

public class PlanInit {

    public RollPlanOperation initRollPlanOperation(RollType rollType){
        return new RollPlanOperation(LocalDate.now(), rollType, 210);
    }
}
