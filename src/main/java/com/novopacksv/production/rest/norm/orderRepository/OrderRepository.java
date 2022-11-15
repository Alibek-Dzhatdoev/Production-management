package com.novopacksv.production.rest.norm.orderRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.novopacksv.production.model.orderModel.Order;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {

    List<Order> findAllByDeliveryDateBetween(LocalDate fromDate, LocalDate toDate);

    Order findFirstByOrderByDeliveryDateDesc();

    List<Order> findAllByActualDeliveryDateIsNull(Sort sort);

    List<Order> findAllByActualDeliveryDateIsNotNull(Sort sort);

    List<Order> findAllByActualDeliveryDateAfter(LocalDate fromDate, Sort sort);

}
