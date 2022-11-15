package com.novopacksv.production.dto.order;

import com.novopacksv.production.validator.ExistInDb;
import com.novopacksv.production.validator.LocalDateFormat;
import com.novopacksv.production.validator.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import com.novopacksv.production.model.orderModel.Client;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class OrderRequest {

    @NotNull(message = "client id is a required field!")
    @ExistInDb(value = Client.class, message = "there are no clients with this id!")
    private Long clientId;

    @NotBlank(message = "city is a required field!")
    @Size(max = 50, message = "city must be less then 50 symbols long!")
    private String city;

    @NotBlank(message = "delivery date is a required field!")
    @LocalDateFormat(message = "incorrect delivery date format!")
    private String deliveryDate;

    @NotNull(message = "is_important is a required field!")
    private Boolean isImportant;

    @LocalDateFormat(message = "incorrect actual delivery date format!")
    @PastOrPresent(message = "actual delivery date can not be in future!")
    private String actualDeliveryDate;

}
