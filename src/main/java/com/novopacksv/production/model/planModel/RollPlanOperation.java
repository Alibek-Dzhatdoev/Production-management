package com.novopacksv.production.model.planModel;

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
@Table(name = "roll_plan_operation")
public class RollPlanOperation extends BaseEntity {

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private RollType rollType;

    @Column(name = "quantity", nullable = false)
    private Integer rollQuantity;
}
