package com.novopacksv.production.dto.product;

import com.novopacksv.production.validator.EnumValue;
import com.novopacksv.production.validator.ExistInDb;
import com.novopacksv.production.validator.LocalDateFormat;
import com.novopacksv.production.validator.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import com.novopacksv.production.model.productModel.ProductOperationType;
import com.novopacksv.production.model.productModel.ProductType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ProductOperationRequest {

    @NotBlank(message = "field operation date can't be empty!")
    @LocalDateFormat(message = "incorrect operation date format!")
    @PastOrPresent(message = "date can't be in future!")
    private String operationDate;

    @NotNull(message = "field can't be empty!")
    @ExistInDb(value = ProductType.class, message = "product type with this id does not exist!")
    private Long productTypeId;

    @NotBlank(message = "field operation type can't be empty!")
    @EnumValue(value = ProductOperationType.class, message = "operation type was not found!")
    private String operationType;

    @NotNull(message = "field amount can't be empty")
    @Positive(message = "amount can't be negative!")
    private Integer amount;
}
