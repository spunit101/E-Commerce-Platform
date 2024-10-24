package com.spunit.ProductManagement.repository;

import com.spunit.ProductManagement.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {
}