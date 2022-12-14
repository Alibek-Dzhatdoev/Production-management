package com.novopacksv.production.repository.rollRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.novopacksv.production.model.rollModel.RollType;

import java.util.List;

@Repository
public interface RollTypeRepository extends JpaRepository <RollType, Long> {

    List<RollType> findAllByThickness(Double thickness);

    List<RollType> findAllByColorCode(String colorCode);
}