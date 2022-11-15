package com.novopacksv.production.dto.order;

import com.novopacksv.production.dto.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse extends BaseEntityResponse {

    private Long orderId;

    private Long productTypeId;

    private Integer amount;

}
