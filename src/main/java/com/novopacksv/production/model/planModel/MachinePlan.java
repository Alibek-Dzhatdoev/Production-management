package com.novopacksv.production.model.planModel;

import com.novopacksv.production.model.productModel.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.novopacksv.production.model.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "machine_plan")
public class MachinePlan extends BaseEntity {

    @Column(name = "machine_number", nullable = false)
    private Integer machineNumber;

    @Column(name = "time_start", nullable = false)
    private LocalDateTime timeStart;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private ProductType productType;

    @OneToMany(mappedBy = "machinePlan")
    private List<MachinePlanItem> machinePlanItems;

    @Column(name = "is_important", nullable = false)
    private Boolean isImportant;

}
