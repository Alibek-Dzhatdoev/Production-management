package com.novopacksv.production.service.roll;

import com.novopacksv.production.model.rollModel.RollLeftOver;
import com.novopacksv.production.model.rollModel.RollType;
import com.novopacksv.production.service.BaseEntityService;

import java.time.LocalDate;
import java.util.List;

public interface RollLeftOverService extends BaseEntityService<RollLeftOver> {

    List<RollLeftOver> findAllByDate(LocalDate date);

    RollLeftOver findByRollTypeIdAndDate(Long rollTypeId, LocalDate date);

    RollLeftOver findLastRollLeftOverByRollType(RollType rollType);

    void createNewLeftOverAndSave(RollType rollType);

    RollLeftOver getTotalLeftOver(LocalDate date);

}