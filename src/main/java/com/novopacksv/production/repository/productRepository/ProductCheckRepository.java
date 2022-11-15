package com.novopacksv.production.repository.productRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.novopacksv.production.model.productModel.ProductCheck;

import java.util.Optional;

@Repository
public interface ProductCheckRepository extends JpaRepository<ProductCheck, Long> {

    Optional<ProductCheck> findByProductType_Id(Long id);
}
