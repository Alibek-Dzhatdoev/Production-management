package com.novopacksv.production.repository.planRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.novopacksv.production.model.planModel.ProductPlanOperation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductPlanOperationRepository extends JpaRepository<ProductPlanOperation, Long> {

    List <ProductPlanOperation> findByProductType_IdAndDateBetween(Long productTypeId,LocalDate fromDate, LocalDate date);

    List<ProductPlanOperation> findAllByRollType_IdAndDateBetween(Long rollTypeId, LocalDate fromDate, LocalDate toDate);

    List<ProductPlanOperation> findAllByDateBetween(LocalDate fromDate, LocalDate toDate);
}
