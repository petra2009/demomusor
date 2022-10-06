package com.example.demomusor.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroceryList extends BaseEntity {

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    public GroceryList(Product product) {
        this.product = product;
    }
}
