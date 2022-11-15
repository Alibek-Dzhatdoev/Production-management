package com.novopacksv.production.service.product;

import com.novopacksv.production.model.productModel.ProductType;
import com.novopacksv.production.service.BaseEntityService;

import java.util.List;

public interface ProductTypeService extends BaseEntityService<ProductType> {

    List<ProductType> findAll(String productTypeName);

    List<ProductType> getByRollTypeIdInNorms(Long rollTypeId);
}
