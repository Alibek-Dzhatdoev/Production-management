package com.novopacksv.production.repository.rollRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.novopacksv.production.model.productModel.ProductType;
import com.novopacksv.production.model.rollModel.OperationType;
import com.novopacksv.production.model.rollModel.RollManufactured;
import com.novopacksv.production.model.rollModel.RollOperation;
import com.novopacksv.production.model.rollModel.RollType;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RollOperationRepository extends JpaRepository<RollOperation, Long> {

    List<RollOperation> findAllByRollManufactured_RollTypeAndRollManufactured_ManufacturedDateBetween
            (RollType rollType, LocalDate fromDate, LocalDate toDate);

    List<RollOperation> findAllByOperationTypeAndRollManufactured(OperationType operationType, RollManufactured rollManufactured);

    List<RollOperation> findAllByRollManufactured(RollManufactured rollManufactured);

    List<RollOperation> findAllByRollManufacturedIsIn(List<RollManufactured> rollManufacturedList);

    List<RollOperation> findAllByRollManufactured_RollType_IdAndOperationDateBetween(Long id, LocalDate from, LocalDate to);

    List<RollOperation> findAllByProductTypeAndOperationDateBetweenAndOperationType(ProductType productType,
                                                                                    LocalDate from,
                                                                                    LocalDate to,
                                                                                    OperationType type);
}
