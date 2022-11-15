package com.novopacksv.production.dto.plan;

import lombok.Getter;
import lombok.Setter;
import com.novopacksv.production.model.productModel.ProductType;
import com.novopacksv.production.model.rollModel.RollType;
import com.novopacksv.production.validator.ExistInDb;
import com.novopacksv.production.validator.FutureOrPresent;
import com.novopacksv.production.validator.LocalDateFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ProductPlanOperationRequest {

    @NotBlank(message = "field operation date can't be empty!")
    @LocalDateFormat(message = "incorrect operation date format!")
    @FutureOrPresent(message = "Date should be in future!")
    private String date;

    @NotNull(message = "field productTypeId can't be empty!")
    @ExistInDb(value = ProductType.class, message = "productType with this id does not exist!")
    private Long productTypeId;

    @NotNull(message = "field rollTypeId can't be empty!")
    @ExistInDb(value = RollType.class, message = "rollType with this id does not exist!")
    private Long rollTypeId;

    @NotNull(message = "field rollAmount can't be empty!")
    @Positive(message = "value of rollAmount can't be negative!")
    private Integer rollAmount;

    @NotNull(message = "field productAmount can't be empty!")
    @Positive(message = "value of productAmount can't be negative!")
    private Integer productAmount;
}
