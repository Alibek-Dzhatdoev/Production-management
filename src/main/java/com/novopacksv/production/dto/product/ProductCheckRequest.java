package com.novopacksv.production.dto.product;

import com.novopacksv.production.validator.EnumValue;
import lombok.Getter;
import lombok.Setter;
import com.novopacksv.production.model.rollModel.CheckStatus;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductCheckRequest  {

    @NotBlank(message = "product left over check status is a required field!")
    @EnumValue(value = CheckStatus.class, message = "product left over check status is not found!")
    private String productLeftOverCheckStatus;
}
