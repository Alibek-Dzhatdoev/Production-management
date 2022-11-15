package com.novopacksv.production.dto.order;

import com.novopacksv.production.dto.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse extends BaseEntityResponse {

    private Long clientId;

    private String city;

    private String deliveryDate;

    private Boolean isImportant;

    private Boolean isDelivered;

    private String actualDeliveryDate;

    private String creationDate;

    private Boolean isOverdue;

}
