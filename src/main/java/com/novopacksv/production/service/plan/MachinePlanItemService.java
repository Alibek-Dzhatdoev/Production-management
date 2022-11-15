package com.novopacksv.production.service.plan;

import com.novopacksv.production.model.planModel.MachinePlan;
import com.novopacksv.production.model.planModel.MachinePlanItem;
import com.novopacksv.production.model.rollModel.RollType;
import com.novopacksv.production.service.BaseEntityService;

public interface MachinePlanItemService extends BaseEntityService<MachinePlanItem> {

    MachinePlanItem findOne(MachinePlan machinePlan, RollType rollType);

}
