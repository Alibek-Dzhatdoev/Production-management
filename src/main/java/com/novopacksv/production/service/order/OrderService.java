package com.novopacksv.production.service.order;

import com.novopacksv.production.model.orderModel.Order;
import com.novopacksv.production.service.BaseEntityService;

import java.time.LocalDate;
import java.util.List;

public interface OrderService extends BaseEntityService<Order> {

    List<Order> findAll(LocalDate fromDeliveryDate, LocalDate toDeliveryDate);

    LocalDate findMaxDeliveryDate();

    List<Order> findAllNotDelivered(String sort);

    List<Order> findAllDelivered(LocalDate date, String sort);
}