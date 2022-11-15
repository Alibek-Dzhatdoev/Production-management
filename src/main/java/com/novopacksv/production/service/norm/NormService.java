package com.novopacksv.production.service.norm;

import com.novopacksv.production.model.normModel.Norm;
import com.novopacksv.production.service.BaseEntityService;

import java.util.List;

public interface NormService extends BaseEntityService<Norm> {

    List<Norm> findNorms(Long rollTypeId);

    void deleteNormsWithoutRolls();

    Norm findOne(Long productTypeId);

    Boolean findFirstByProductTypeId(Long productTypeId);

    Boolean findFirstByRollTypeId(Long rollTypeId);
}
