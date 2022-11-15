package com.novopacksv.production.converter.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.dto.order.OrderItemRequest;
import com.novopacksv.production.model.orderModel.Order;
import com.novopacksv.production.model.orderModel.OrderItem;
import com.novopacksv.production.model.productModel.ProductType;
import com.novopacksv.production.service.order.OrderService;
import com.novopacksv.production.service.product.ProductTypeService;

@Component
public class OrderItemRequestToOrderItemConverter implements Converter<OrderItemRequest, OrderItem> {

    @Autowired
    @Lazy
    private ProductTypeService productTypeService;

    @Autowired
    @Lazy
    private OrderService orderService;

    @Override
    public OrderItem convert(OrderItemRequest source) {
        ProductType productType = productTypeService.findById(source.getProductTypeId());
        Order order = orderService.findById(source.getOrderId());
        OrderItem result = new OrderItem();
        result.setProductType(productType);
        result.setAmount(source.getAmount());
        result.setOrder(order);
        return result;
    }

}