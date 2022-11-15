package com.novopacksv.production.service.roll;

import com.novopacksv.production.model.rollModel.RollType;
import com.novopacksv.production.service.BaseEntityService;

import java.util.List;

public interface RollTypeService extends BaseEntityService<RollType> {

    List<RollType> findAll(Double thickness);

    List<RollType> findAll(String colorCode);
}