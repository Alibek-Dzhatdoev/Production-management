package com.novopacksv.production.service.plan;

import com.novopacksv.production.model.planModel.RollPlanOperation;
import com.novopacksv.production.service.BaseEntityService;

import java.time.LocalDate;
import java.util.List;

public interface RollPlanOperationService extends BaseEntityService<RollPlanOperation>{

    List<RollPlanOperation> findAll(Long rollTypeId, LocalDate fromDate, LocalDate toDate);
}
