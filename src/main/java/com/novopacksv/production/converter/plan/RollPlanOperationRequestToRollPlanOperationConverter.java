package com.novopacksv.production.converter.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.dto.plan.RollPlanOperationRequest;
import com.novopacksv.production.model.planModel.RollPlanOperation;
import com.novopacksv.production.service.roll.RollTypeService;

import java.time.LocalDate;

@Component
public class RollPlanOperationRequestToRollPlanOperationConverter implements
        Converter<RollPlanOperationRequest, RollPlanOperation> {

    @Autowired
    @Lazy
    private RollTypeService rollTypeService;

    @Autowired
    @Lazy
    private ConversionService conversionService;

    @Override
    public RollPlanOperation convert(RollPlanOperationRequest request) {
        RollPlanOperation rollPlanOperation = new RollPlanOperation();
        LocalDate date = conversionService.convert(request.getDate(), LocalDate.class);
        rollPlanOperation.setDate(date);
        rollPlanOperation.setRollType(rollTypeService.findById(request.getRollTypeId()));
        rollPlanOperation.setRollQuantity(request.getRollAmount());
        return rollPlanOperation;
    }
}
