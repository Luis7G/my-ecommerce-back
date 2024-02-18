package com.myecommerce.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myecommerce.model.Product;
import com.myecommerce.repositories.ProductRepository;

@RestController
@RequestMapping("/products")
// @RestController(value = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    // public List<String> getAllProducts() {
    public List<Product> getAllProducts() {
        // return Arrays.asList(new String[] { "Producto 1", "Producto 2" });
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable String id) {
        Optional<Product> optProduct = productRepository.findById(Integer.parseInt(id));
        if (optProduct.isPresent()) {
            // return optProduct.get();
            return ResponseEntity.ok(optProduct.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @PatchMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
        productRepository.deleteById(Integer.parseInt(id));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
