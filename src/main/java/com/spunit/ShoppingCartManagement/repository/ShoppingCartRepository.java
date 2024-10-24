package com.spunit.ShoppingCartManagement.repository;

import com.spunit.ShoppingCartManagement.model.ShoppingCart;
import com.spunit.UserManagement.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart findByAuthUser(AuthUser authUser);

}

