package com.novopacksv.production.converter.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.dto.order.OrderRequest;
import com.novopacksv.production.model.orderModel.Client;
import com.novopacksv.production.model.orderModel.Order;
import com.novopacksv.production.service.order.ClientService;

import java.time.LocalDate;

@Component
public class OrderRequestToOrderConverter implements Converter<OrderRequest, Order> {

    @Autowired
    @Lazy
    private ClientService clientService;

    @Autowired
    @Lazy
    private ConversionService conversionService;

    @Override
    public Order convert(OrderRequest source) {
        Client client = clientService.findById(source.getClientId());
        LocalDate deliveryDate = conversionService.convert(source.getDeliveryDate(), LocalDate.class);
        LocalDate actualDeliveryDate = conversionService.convert(source.getActualDeliveryDate(), LocalDate.class);
        Order result = new Order();
        result.setClient(client);
        result.setCity(source.getCity());
        result.setDeliveryDate(deliveryDate);
        result.setIsImportant(source.getIsImportant());
        result.setActualDeliveryDate(actualDeliveryDate);
        return result;
    }

}
