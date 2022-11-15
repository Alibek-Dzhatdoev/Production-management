package com.novopacksv.production.service.plan;

import com.novopacksv.production.model.planModel.ProductPlanOperation;
import com.novopacksv.production.model.productModel.ProductType;
import com.novopacksv.production.model.rollModel.RollType;
import com.novopacksv.production.service.BaseEntityService;

import java.time.LocalDate;
import java.util.List;

public interface ProductPlanOperationService extends BaseEntityService<ProductPlanOperation> {

    List<ProductPlanOperation> getAll(Long productTypeId, LocalDate fromDate, LocalDate toDate);

    List<ProductPlanOperation> getAll(LocalDate fromDate, LocalDate toDate);

    List<ProductPlanOperation> getAllByRollTypeId(Long rollTypeId, LocalDate fromDate, LocalDate toDate);

    Integer getRollToMachinePlanAmount(ProductType productType, RollType rollType, LocalDate date);

}
