package com.novopacksv.production.model.orderModel;

import com.novopacksv.production.model.productModel.ProductType;
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
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Order order;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private ProductType productType;

    @Column(name = "amount", nullable = false)
    private Integer amount;

}
