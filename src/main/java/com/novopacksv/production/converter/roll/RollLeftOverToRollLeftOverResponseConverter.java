package com.novopacksv.production.converter.roll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.dto.roll.RollLeftOverResponse;
import com.novopacksv.production.model.rollModel.RollLeftOver;

@Component
public class RollLeftOverToRollLeftOverResponseConverter implements Converter<RollLeftOver, RollLeftOverResponse> {

    @Autowired
    @Lazy
    private ConversionService conversionService;

    @Override
    public RollLeftOverResponse convert(RollLeftOver source) {
        String date = conversionService.convert(source.getDate(), String.class);
        RollLeftOverResponse result = new RollLeftOverResponse();
        result.setDate(date);
        result.setRollTypeId(source.getRollType().getId());
        result.setAmount(source.getAmount());
        return result;
    }

}