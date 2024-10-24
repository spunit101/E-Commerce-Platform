package com.spunit.OrderManagement.model;

import com.spunit.UserManagement.model.AuthUser;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AuthUser authUser;

    @OneToMany
    private List<OrderItem> items;

    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private String status; // e.g., "PENDING", "COMPLETED", "CANCELLED"

}

