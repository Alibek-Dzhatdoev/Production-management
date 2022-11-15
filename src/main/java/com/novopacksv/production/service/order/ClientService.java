package com.novopacksv.production.service.order;

import com.novopacksv.production.model.orderModel.Client;
import com.novopacksv.production.service.BaseEntityService;

import java.util.List;

public interface ClientService extends BaseEntityService<Client> {

    List<Client> findAll(String sort);

}