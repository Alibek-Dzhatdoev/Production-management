package com.novopacksv.production.converter.order;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.dto.order.ClientResponse;
import com.novopacksv.production.model.orderModel.Client;

@Component
public class ClientToClientResponseConverter implements Converter<Client, ClientResponse> {

    @Override
    public ClientResponse convert(Client source) {
        ClientResponse result = new ClientResponse();
        result.setId(source.getId());
        result.setName(source.getName());
        return result;
    }

}