package com.spunit.ShoppingCartManagement.model;

import com.spunit.UserManagement.model.AuthUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private AuthUser authUser;

    @ElementCollection
    private Map<Long, Integer> products = new HashMap<>();

}

