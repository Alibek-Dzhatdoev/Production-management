package com.novopacksv.production.converter.order;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.dto.order.ClientRequest;
import com.novopacksv.production.model.orderModel.Client;

@Component
public class ClientRequestToClientConverter implements Converter<ClientRequest, Client> {

    @Override
    public Client convert(ClientRequest source) {
        Client result = new Client();
        result.setName(source.getName());
        return result;
    }

}