package com.novopacksv.production.service.roll;

import org.springframework.stereotype.Service;
import com.novopacksv.production.model.productModel.ProductType;
import com.novopacksv.production.model.rollModel.OperationType;
import com.novopacksv.production.model.rollModel.RollManufactured;
import com.novopacksv.production.model.rollModel.RollOperation;
import com.novopacksv.production.model.rollModel.RollType;
import com.novopacksv.production.service.BaseEntityService;

import java.time.LocalDate;
import java.util.List;

@Service
public interface RollOperationService extends BaseEntityService<RollOperation> {

    List<RollOperation> findAllByRollTypeIdAndManufacturedPeriod(Long id, LocalDate from, LocalDate to);

    List<RollOperation> findAllByRollTypeIdAndOperationPeriod(Long id, LocalDate from, LocalDate to);

    List<RollOperation> findAllByRollTypeId(Long id);

    List<RollOperation> findAllManufacturedOperationsByRollManufactured(RollManufactured rollManufactured);

    List<RollOperation> findAllUsedOperationsByRollManufactured(RollManufactured rollManufactured);

    List<RollOperation> findAllByRollTypeAndManufacturedDateBetween(RollType rollType, LocalDate fromDate, LocalDate toDate);

    List<RollOperation> findAll(ProductType productType, LocalDate from, LocalDate to, OperationType type);

}