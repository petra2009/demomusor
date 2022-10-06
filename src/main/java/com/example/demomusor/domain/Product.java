package com.example.demomusor.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Column
    private String name;            //наименование продукта

    @Column
    private BigDecimal price;       //цена продукта

    @ManyToOne
    @JoinColumn(name="category_Id")         //категория продукта
    private Category category;

    @OneToMany(mappedBy = "product")        //один продукт может быть во многих списках
    private List<GroceryList> groceryLists;

    public Product(String name, BigDecimal price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
