package com.novopacksv.production.service.roll;

import com.novopacksv.production.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.novopacksv.production.model.rollModel.RollManufactured;
import com.novopacksv.production.model.rollModel.RollType;

import java.time.LocalDate;
import java.util.List;

@Service
public interface RollManufacturedService {

    RollManufactured findOne(LocalDate manufacturedDate, Long rollTypeId) throws ResourceNotFoundException;

    RollManufactured findOneOrCreate(LocalDate manufacturedDate, RollType rollType);

    List<RollManufactured> findAll(Long rollTypeId);

    List<RollManufactured> findAll(LocalDate manufacturedDate);

    List<RollManufactured> findAll(LocalDate fromManufacturedDate, LocalDate toManufacturedDate);

    List<RollManufactured> findAll(LocalDate fromManufacturedDate, LocalDate toManufacturedDate, Long rollTypeId);

    Integer getManufacturedRollAmount(RollManufactured rollManufactured);

    Integer getUsedRollAmount(RollManufactured rollManufactured);

    Integer getLeftOverAmount(RollManufactured manufactured);

    void setReadyToUseTrue(LocalDate from, LocalDate to);

}