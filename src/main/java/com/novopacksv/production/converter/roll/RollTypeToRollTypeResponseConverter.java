package com.novopacksv.production.converter.roll;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.dto.roll.RollTypeResponse;
import com.novopacksv.production.model.rollModel.RollType;

@Component
public class RollTypeToRollTypeResponseConverter implements Converter<RollType, RollTypeResponse> {

    @Override
    public RollTypeResponse convert(RollType source) {
        RollTypeResponse result = new RollTypeResponse();
        result.setId(source.getId());
        result.setNote(source.getNote());
        result.setThickness(source.getThickness());
        result.setMinWeight(source.getMinWeight());
        result.setMaxWeight(source.getMaxWeight());
        result.setLength(source.getLength());
        result.setColorCode(source.getColorCode());
        return result;
    }

}