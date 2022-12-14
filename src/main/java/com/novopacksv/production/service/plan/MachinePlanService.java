package com.novopacksv.production.service.plan;

import com.novopacksv.production.model.planModel.MachinePlan;
import com.novopacksv.production.service.BaseEntityService;

import java.time.LocalDate;
import java.util.List;

public interface MachinePlanService extends BaseEntityService<MachinePlan> {

    Double getDuration(MachinePlan machinePlan);

    List<MachinePlan> findByMachineNumberAndDate(Integer machineNumber, LocalDate date);

    List<MachinePlan> findSort(Integer machineNumber, LocalDate date, String sort);

    List<MachinePlan> findByProductForMachinePlan(Long productTypeId, LocalDate date);

    Integer countProductAmountForMachinePlan(Long productTypeId, LocalDate date);

    Integer getProductAmount(MachinePlan machinePlan);

}
