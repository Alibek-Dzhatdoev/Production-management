package com.novopacksv.production.repository.normRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.novopacksv.production.model.normModel.Norm;
import com.novopacksv.production.model.rollModel.RollType;

import java.util.List;
import java.util.Optional;

@Repository
public interface NormRepository extends JpaRepository<Norm, Long> {

    @Query("from Norm n join n.rollTypes r where r.id = ?1")
    List<Norm> getByRollTypeId(Long rollTypeId);

    void deleteNormsByRollTypesNull();

    Optional <Norm> findByProductType_Id(Long productTypeId);

    Norm findFirstByProductType_Id(Long productTypeId);

    Norm findFirstByRollTypesContains(RollType rollType);
}
