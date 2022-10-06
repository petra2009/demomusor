package com.example.demomusor.repository;

import com.example.demomusor.domain.Category;
import com.example.demomusor.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findProductById(int id);

    List<Product> findAllByCategory(Optional<Category> category);
}
