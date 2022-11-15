package com.novopacksv.production.rest.order;

import com.novopacksv.production.converter.ModelConversionService;
import com.novopacksv.production.dto.order.OrderItemResponse;
import com.novopacksv.production.model.orderModel.Order;
import com.novopacksv.production.model.orderModel.OrderItem;
import com.novopacksv.production.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "${spring.rest.api-url-prefix}/orders/{orderId}/order-items", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CMO', 'ROLE_ECONOMIST', 'ROLE_FULL_ACCESS')")
@RequiredArgsConstructor
public class OrderItemsOfOrderController {

    private final OrderService orderService;

    private final ModelConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<OrderItemResponse>> getAllForOrder(@PathVariable("orderId") Long orderId) {
        Order order = orderService.findById(orderId);
        List<OrderItem> orderItems = order.getOrderItems();
        List<OrderItemResponse> response = conversionService.convert(orderItems, OrderItemResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}