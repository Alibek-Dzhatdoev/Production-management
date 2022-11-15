package com.novopacksv.production.model.planModel;

import com.novopacksv.production.model.productModel.ProductType;
import com.novopacksv.production.model.rollModel.RollType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.novopacksv.production.model.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "product_plan_operation")
public class ProductPlanOperation extends BaseEntity {

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private ProductType productType;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private RollType rollType;

    @Column(name = "roll_amount", nullable = false)
    private Integer rollAmount;

    @Column(name = "product_amount", nullable = false)
    private Integer productAmount;
}
