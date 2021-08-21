package com.codecool.springshopapplication.controller;

import com.codecool.springshopapplication.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
//@RequestMapping("/products") // and with this you do not need to repeat /products
public class ProductController {

    @GetMapping("/products")
    public List<Product> getProducts(){
        return List.of();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable String id){
        Product mockProduct = new Product();

        mockProduct.setId(1);
        mockProduct.setName("Test product");

        return mockProduct;
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product){
        // add product
        product.setId(11);
        return product;
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable long id, @RequestBody Product product){
        // update product
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK) // optional - default set on this
    public void deleteProduct(@PathVariable long id){
        // delete product
    }

}
