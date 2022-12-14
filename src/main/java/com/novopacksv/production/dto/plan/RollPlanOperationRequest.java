package com.novopacksv.production.dto.plan;

import com.novopacksv.production.validator.ExistInDb;
import com.novopacksv.production.validator.FutureOrPresent;
import com.novopacksv.production.validator.LocalDateFormat;
import lombok.Getter;
import lombok.Setter;
import com.novopacksv.production.model.rollModel.RollType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class RollPlanOperationRequest {

    @NotBlank(message = "field date can't be empty!")
    @LocalDateFormat(message = "incorrect operation date format!")
    @FutureOrPresent(message = "date should be in future!")
    private String date;

    @NotNull(message = "field rollTypeId can't be empty!")
    @ExistInDb(value = RollType.class, message = "RollType with this id does not exist!")
    private Long rollTypeId;

    @NotNull(message = "field rollAmount can't be empty!")
    @Positive(message = "value of rollAmount can't be negative!")
    private Integer rollAmount;
}
