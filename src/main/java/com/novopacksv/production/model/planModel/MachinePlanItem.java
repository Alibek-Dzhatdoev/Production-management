package com.novopacksv.production.model.planModel;

import com.novopacksv.production.model.rollModel.RollType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.novopacksv.production.model.BaseEntity;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "machine_plan_item")
public class MachinePlanItem extends BaseEntity {

    @ManyToOne
    @PrimaryKeyJoinColumn
    private RollType rollType;

    @Column(name = "roll_amount", nullable = false)
    private Integer rollAmount;

    @Column(name = "product_amount", nullable = false)
    private Integer productAmount;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private MachinePlan machinePlan;

}
