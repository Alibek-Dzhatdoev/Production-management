package com.novopacksv.production.converter.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.dto.plan.RollPlanBatchResponse;
import com.novopacksv.production.model.planModel.RollPlanBatch;

@Component
public class RollPlanBatchToRollPlanBatchResponseConverter implements Converter<RollPlanBatch, RollPlanBatchResponse> {

    @Autowired
    @Lazy
    private ConversionService conversionService;

    @Override
    public RollPlanBatchResponse convert(RollPlanBatch source) {
        RollPlanBatchResponse result = new RollPlanBatchResponse();
        String date = conversionService.convert(source.getDate(), String.class);
        result.setDate(date);
        result.setRollTypeId(source.getRollType().getId());
        result.setManufacturedAmount(source.getRollPlanManufacturedAmount());
        result.setUsedAmount(source.getRollPlanUsedAmount());
        return result;
    }
}
