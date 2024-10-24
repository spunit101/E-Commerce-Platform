package com.spunit.OrderManagement.model;


import com.spunit.ProductManagement.model.Products;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Products products;

    private int quantity;
    private BigDecimal price;

}
