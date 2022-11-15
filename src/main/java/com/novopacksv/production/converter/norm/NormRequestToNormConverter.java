package com.novopacksv.production.converter.norm;

import com.novopacksv.production.dto.norm.NormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.model.normModel.Norm;
import com.novopacksv.production.model.rollModel.RollType;
import com.novopacksv.production.service.product.ProductTypeService;
import com.novopacksv.production.service.roll.RollTypeService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NormRequestToNormConverter implements Converter<NormRequest, Norm> {

    @Autowired
    @Lazy
    private RollTypeService rollTypeService;

    @Autowired
    @Lazy
    private ProductTypeService productTypeService;

    @Override
    public Norm convert(NormRequest source) {
        Norm norm = new Norm();
        norm.setNorm(source.getNorm());
        List<RollType> rollTypes =
                source.getRollTypeIds().stream().map((id) -> rollTypeService.findById(id)).collect(Collectors.toList());
        norm.setRollTypes(rollTypes);
        norm.setProductType(productTypeService.findById(source.getProductTypeId()));
        norm.setNormForDay(source.getNormForDay());
        return norm;
    }
}
