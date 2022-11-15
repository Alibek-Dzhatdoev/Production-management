package com.novopacksv.production.converter.product;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.dto.product.ProductCheckResponse;
import com.novopacksv.production.model.productModel.ProductCheck;

@Component
public class ProductCheckToProductCheckResponse implements Converter<ProductCheck, ProductCheckResponse> {

    @Override
    public ProductCheckResponse convert(ProductCheck source) {
        ProductCheckResponse result = new ProductCheckResponse();
        result.setId(source.getId());
        result.setProductTypeId(source.getProductType().getId());
        result.setProductLeftOverCheckStatus(source.getProductLeftOverCheckStatus().name());
        return result;
    }
}
