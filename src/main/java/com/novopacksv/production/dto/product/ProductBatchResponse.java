package com.novopacksv.production.dto.product;

import com.novopacksv.production.dto.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductBatchResponse extends BaseEntityResponse {

    private Long productTypeId;

    private Integer manufacturedAmount;

    private Integer soldAmount;

}
