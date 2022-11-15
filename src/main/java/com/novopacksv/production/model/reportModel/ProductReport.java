package com.novopacksv.production.model.reportModel;

import com.novopacksv.production.model.productModel.ProductType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductReport {

    private ProductType productType;

    private Integer planAmount;

    private Integer actualAmount;

}
