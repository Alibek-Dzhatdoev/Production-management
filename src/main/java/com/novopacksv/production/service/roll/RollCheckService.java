package com.novopacksv.production.service.roll;

import com.novopacksv.production.model.rollModel.RollCheck;
import com.novopacksv.production.model.rollModel.RollType;

import java.util.List;

public interface RollCheckService {

    RollCheck findOneByRollTypeId(Long id);

    List<RollCheck> findAll();

    RollCheck update(RollCheck check);

    void createNewRollCheckAndSave(RollType rollType);

    void setNotCheckedStatusForAll();

}