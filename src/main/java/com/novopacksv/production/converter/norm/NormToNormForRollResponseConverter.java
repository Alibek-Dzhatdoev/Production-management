package com.novopacksv.production.converter.norm;

import com.novopacksv.production.dto.norm.NormForRollResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.model.normModel.Norm;

@Component
public class NormToNormForRollResponseConverter implements Converter<Norm, NormForRollResponse> {

    @Override
    public NormForRollResponse convert(Norm source) {
        NormForRollResponse response = new NormForRollResponse();
        response.setProductTypeId(source.getProductType().getId());
        response.setRollTypes(source.getRollTypes());
        response.setNorm(source.getNorm());
        response.setNormForDay(source.getNormForDay());
        return response;
    }
}
