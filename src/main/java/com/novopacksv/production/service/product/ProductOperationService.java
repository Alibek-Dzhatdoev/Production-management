package com.novopacksv.production.service.product;

import com.novopacksv.production.model.productModel.ProductOperation;
import com.novopacksv.production.service.BaseEntityService;

import java.time.LocalDate;
import java.util.List;

public interface ProductOperationService extends BaseEntityService<ProductOperation> {

    List<ProductOperation> findAllOperationBetweenDatesByTypeId(Long productTypeId,
                                                                LocalDate fromDate, LocalDate toDate);
}
