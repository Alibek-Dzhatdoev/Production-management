package com.novopacksv.production.converter.norm;

import com.novopacksv.production.dto.norm.NormResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.model.BaseEntity;
import com.novopacksv.production.model.normModel.Norm;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NormToNormResponseConverter implements Converter<Norm, NormResponse> {

    @Override
    public NormResponse convert(Norm source) {
        NormResponse normResponse = new NormResponse();
        normResponse.setNorm(source.getNorm());
        List<Long> rollTypeIds = source.getRollTypes().stream()
                .map(BaseEntity::getId).collect(Collectors.toList());
        normResponse.setRollTypeIds(rollTypeIds);
        normResponse.setProductTypeId(source.getProductType().getId());
        normResponse.setNormForDay(source.getNormForDay());
        return normResponse;
    }
}
