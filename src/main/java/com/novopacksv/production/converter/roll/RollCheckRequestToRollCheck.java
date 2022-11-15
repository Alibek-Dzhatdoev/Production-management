package com.novopacksv.production.converter.roll;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.dto.roll.RollCheckRequest;
import com.novopacksv.production.model.rollModel.CheckStatus;
import com.novopacksv.production.model.rollModel.RollCheck;

@Component
public class RollCheckRequestToRollCheck implements Converter<RollCheckRequest, RollCheck> {

    @Override
    public RollCheck convert(RollCheckRequest source) {
        RollCheck result = new RollCheck();
        result.setRollLeftOverCheckStatus(Enum.valueOf(CheckStatus.class, source.getRollLeftOverCheckStatus()));
        return result;
    }

}