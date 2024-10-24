package com.spunit.ProductManagement.controller;

import com.spunit.ProductManagement.model.Products;
import com.spunit.ProductManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        Products products = productService.getProductById(id);
        return products != null ? ResponseEntity.ok(products) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Products> createProduct(@RequestBody Products products) {
        Products createdProducts = productService.createProduct(products);
        return ResponseEntity.status(201).body(createdProducts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products productsDetails) {
        Products updatedProducts = productService.updateProduct(id, productsDetails);
        return updatedProducts != null ? ResponseEntity.ok(updatedProducts) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}

