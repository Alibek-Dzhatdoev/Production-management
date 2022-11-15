package com.novopacksv.production.rest.norm.orderRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.novopacksv.production.model.orderModel.Order;
import com.novopacksv.production.model.orderModel.OrderItem;
import com.novopacksv.production.model.productModel.ProductType;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findAllByOrder(Order order);

    List<OrderItem> findAllByProductTypeAndOrder_DeliveryDateBetween(ProductType productType,
                                                                     LocalDate fromDate, LocalDate toDate);

    List<OrderItem> findAllByProductTypeAndOrder_ActualDeliveryDateIsNullAndOrder_DeliveryDateLessThanEqual
            (ProductType productType, LocalDate date);
}