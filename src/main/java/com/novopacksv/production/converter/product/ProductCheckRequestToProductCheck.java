package com.novopacksv.production.converter.product;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.dto.product.ProductCheckRequest;
import com.novopacksv.production.model.productModel.ProductCheck;
import com.novopacksv.production.model.rollModel.CheckStatus;

@Component
public class ProductCheckRequestToProductCheck implements Converter<ProductCheckRequest, ProductCheck> {

    @Override
    public ProductCheck convert(ProductCheckRequest source) {
        ProductCheck result = new ProductCheck();
        result.setProductLeftOverCheckStatus(Enum.valueOf(CheckStatus.class, source.getProductLeftOverCheckStatus()));
        return result;
    }
}
