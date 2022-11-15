package com.novopacksv.production.dto.order;

import com.novopacksv.production.validator.ExistInDb;
import lombok.Getter;
import lombok.Setter;
import com.novopacksv.production.model.orderModel.Order;
import com.novopacksv.production.model.productModel.ProductType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class OrderItemRequest {

    @NotNull(message = "order id is a required field!")
    @ExistInDb(value = Order.class, message = "there are no orders with this id!")
    private Long orderId;

    @NotNull(message = "product type id is a required field!")
    @ExistInDb(value = ProductType.class, message = "there are no product types with this id!")
    private Long productTypeId;

    @NotNull(message = "product amount is a required field!")
    @Positive(message = "product amount must be greater then 0!")
    private Integer amount;

}
