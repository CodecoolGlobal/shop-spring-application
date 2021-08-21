package com.codecool.springshopapplication.services;

import com.codecool.springshopapplication.model.Product;
import com.codecool.springshopapplication.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Optional<Product> getProductById(final long id) {
        return repository.findById(id);
    }

    public Product createProduct(final Product product) {
        return repository.save(product);
    }

    public Product updateProduct(final long id, final Product product) {
        final Optional<Product> optionalProduct = repository.findById(id);
        final Product updatedProduct = optionalProduct.map(p -> {
            p.setBrand(product.getBrand());
            p.setName(product.getName());
            return p;
        }).orElse(product);
        return repository.save(updatedProduct);
    }

    public void deleteProduct(final long id) {
        repository.deleteById(id);
    }
}
