package com.novopacksv.production.model.planModel;

import com.novopacksv.production.model.productModel.ProductType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProductPlanBatch {

    private LocalDate date;

    private ProductType productType;

    private Integer manufacturedAmount;

    private Integer usedAmount;
}
