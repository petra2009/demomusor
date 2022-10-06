package com.example.demomusor.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity{

    @Column
    private String category;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(String category) {
        this.category = category;
    }


}
