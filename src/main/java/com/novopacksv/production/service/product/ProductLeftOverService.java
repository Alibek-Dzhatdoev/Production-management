package com.novopacksv.production.service.product;

import com.novopacksv.production.model.productModel.ProductLeftOver;
import com.novopacksv.production.model.productModel.ProductOperation;
import com.novopacksv.production.model.productModel.ProductType;
import com.novopacksv.production.service.BaseEntityService;

import java.time.LocalDate;
import java.util.List;

public interface ProductLeftOverService extends BaseEntityService<ProductLeftOver> {

    List<ProductLeftOver> findOnDate(LocalDate date);

    List<ProductLeftOver> findLatest();

    ProductLeftOver findByProductType_IdOnDate(Long productTypeId, LocalDate date);

    ProductLeftOver findByProductTypeId(Long productTypeId);

    Boolean isSoldOperation(ProductOperation productOperation);

    ProductLeftOver saveByProductType (ProductType productType);
}
