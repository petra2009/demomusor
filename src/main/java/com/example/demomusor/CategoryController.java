package com.example.demomusor;

import com.example.demomusor.domain.Category;
import com.example.demomusor.domain.Product;

import com.example.demomusor.repository.CategoryRepository;
import com.example.demomusor.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private List<Product> products;


    public CategoryController(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @GetMapping
    public String getCategory(Model model) {
        products = new ArrayList<>();
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categoriesToForm", categories);
        return "index";
    }

    @PostMapping("addCategory")
    public String setCategory(@RequestParam String category) {
        Category category1 = new Category(category);
        categoryRepository.save(category1);
        return "redirect:/all";
    }

    @PostMapping("addProduct")
    public String setProduct(@RequestParam(name = "category") Category category,
                             @RequestParam(name = "product") String name,
                             @RequestParam(name = "price") BigDecimal price) {
        Product product1 = new Product(name, price, category);
        productRepository.save(product1);
        return "redirect:/all";
    }

    @GetMapping("all")
    public String getCategoryAll(Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "index";
    }

    @GetMapping("/category/{id}")
    public String getProducts(@PathVariable("id") Integer id,
                              Model model) {

        //System.out.println(category);
        Optional<Category> category = categoryRepository.findById(id);
        List<Product> products = productRepository.findAllByCategory(category);
        //System.out.println(Category.toString(category));
        model.addAttribute("category", products);
        return "index";
    }

    @GetMapping("/product/{prodId}")
    public String addProductToList(@PathVariable("prodId") Integer prodId,
                                   Model model) {
        Product product = productRepository.findProductById(prodId);


            products.add(product);


        model.addAttribute("listProducts", products);
        System.out.println(products);
        return "index";
    }


}
