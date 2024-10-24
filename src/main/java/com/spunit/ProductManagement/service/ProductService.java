package com.spunit.ProductManagement.service;

import com.spunit.ProductManagement.model.Products;
import com.spunit.ProductManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Products getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Products createProduct(Products products) {
        return productRepository.save(products);
    }

    public Products updateProduct(Long id, Products productsDetails) {
        Products products = getProductById(id);
        if (products != null) {
            products.setName(productsDetails.getName());
            products.setDescription(productsDetails.getDescription());
            products.setPrice(productsDetails.getPrice());
            products.setImageUrl(productsDetails.getImageUrl());
            return productRepository.save(products);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

