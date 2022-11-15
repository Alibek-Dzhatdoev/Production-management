package com.novopacksv.production.service.order;

import com.novopacksv.production.model.orderModel.OrderItem;
import com.novopacksv.production.model.productModel.ProductType;
import com.novopacksv.production.service.BaseEntityService;

import java.time.LocalDate;
import java.util.List;

public interface OrderItemService extends BaseEntityService<OrderItem> {

    List<OrderItem> findAll(Long orderId);

    List<OrderItem> findAll(ProductType productType, LocalDate fromDate, LocalDate toDate);

    List<OrderItem> findAllNotDelivered(ProductType productType, LocalDate toDate);

}
