package com.novopacksv.production.model.normModel;

import com.novopacksv.production.model.productModel.ProductType;
import com.novopacksv.production.model.rollModel.RollType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.novopacksv.production.model.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "norm")
public class Norm extends BaseEntity {

    @ManyToMany
    @JoinTable(name = "norm_roll_type",
            joinColumns = {@JoinColumn(name = "norm_id")},
            inverseJoinColumns = {@JoinColumn(name = "roll_type_id")})
    private List<RollType> rollTypes;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private ProductType productType;

    @Column(name = "norm", nullable = false)
    private Integer norm;

    @Column(name = "norm_for_day", nullable = false)
    private Integer normForDay;
}
