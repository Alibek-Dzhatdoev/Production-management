package com.novopacksv.production.converter.roll;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.dto.roll.RollCheckResponse;
import com.novopacksv.production.model.rollModel.RollCheck;

@Component
public class RollCheckToRollCheckResponse implements Converter<RollCheck, RollCheckResponse> {

    @Override
    public RollCheckResponse convert(RollCheck source) {
        RollCheckResponse result = new RollCheckResponse();
        result.setId(source.getId());
        result.setRollTypeId(source.getRollType().getId());
        result.setRollLeftOverCheckStatus(source.getRollLeftOverCheckStatus().name());
        return result;
    }

}