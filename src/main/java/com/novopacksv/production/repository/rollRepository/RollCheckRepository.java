package com.novopacksv.production.repository.rollRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.novopacksv.production.model.rollModel.RollCheck;

import java.util.Optional;

@Repository
public interface RollCheckRepository extends JpaRepository<RollCheck, Long> {

    Optional<RollCheck> findByRollType_Id(Long rollTypeId);

}