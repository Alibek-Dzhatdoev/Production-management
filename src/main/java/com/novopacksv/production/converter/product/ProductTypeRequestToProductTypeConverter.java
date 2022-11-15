package com.novopacksv.production.converter.product;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.dto.product.ProductTypeRequest;
import com.novopacksv.production.model.productModel.ProductType;

@Component
public class ProductTypeRequestToProductTypeConverter implements Converter<ProductTypeRequest, ProductType> {

    @Override
    public ProductType convert(ProductTypeRequest source) {
        ProductType result = new ProductType();
        result.setName(source.getName());
        result.setWeight(source.getWeight());
        result.setColorCode(source.getColorCode());
        return result;
    }

}
