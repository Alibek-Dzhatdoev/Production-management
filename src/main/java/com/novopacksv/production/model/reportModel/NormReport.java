package com.novopacksv.production.model.reportModel;

import com.novopacksv.production.model.productModel.ProductType;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.novopacksv.production.model.rollModel.RollOperation;

import java.util.List;

@Data
@NoArgsConstructor
public class NormReport {

    private ProductType productType;

    private List<RollOperation> rollOperations;

    private Integer productPlanAmount;

    private Integer productActualAmount;

}
