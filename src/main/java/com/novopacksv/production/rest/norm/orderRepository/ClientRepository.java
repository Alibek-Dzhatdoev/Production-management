package com.novopacksv.production.rest.norm.orderRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.novopacksv.production.model.orderModel.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository <Client, Long> {

    Optional<Client> findByName(String name);

}
