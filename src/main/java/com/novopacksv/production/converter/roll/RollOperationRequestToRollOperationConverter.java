package com.novopacksv.production.converter.roll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.dto.roll.RollOperationRequest;
import com.novopacksv.production.model.rollModel.OperationType;
import com.novopacksv.production.model.rollModel.RollManufactured;
import com.novopacksv.production.model.rollModel.RollOperation;
import com.novopacksv.production.model.rollModel.RollType;
import com.novopacksv.production.service.product.ProductTypeService;
import com.novopacksv.production.service.roll.RollManufacturedService;
import com.novopacksv.production.service.roll.RollTypeService;

import java.time.LocalDate;

@Component
public class RollOperationRequestToRollOperationConverter implements Converter<RollOperationRequest, RollOperation> {

    @Autowired
    @Lazy
    private ConversionService conversionService;

    @Autowired
    @Lazy
    private RollTypeService rollTypeService;

    @Autowired
    @Lazy
    private RollManufacturedService rollManufacturedService;

    @Autowired
    @Lazy
    private ProductTypeService productTypeService;

    @Override
    public RollOperation convert(RollOperationRequest source) {
        LocalDate operationDate = conversionService.convert(source.getOperationDate(), LocalDate.class);
        LocalDate manufacturedDate = conversionService.convert(source.getManufacturedDate(), LocalDate.class);
        RollType rollType = rollTypeService.findById(source.getRollTypeId());
        RollManufactured rollManufactured = rollManufacturedService.findOneOrCreate(manufacturedDate, rollType);
        RollOperation result = new RollOperation();
        result.setOperationDate(operationDate);
        result.setRollManufactured(rollManufactured);
        result.setOperationType(OperationType.valueOf(source.getOperationType().toUpperCase()));
        result.setRollAmount(source.getRollAmount());
        if(source.getProductTypeIdForUseOperation() != null){
            result.setProductType(productTypeService.findById(source.getProductTypeIdForUseOperation()));
        }
        return result;
    }

}