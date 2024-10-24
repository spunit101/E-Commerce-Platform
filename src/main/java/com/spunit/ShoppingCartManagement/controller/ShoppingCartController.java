package com.spunit.ShoppingCartManagement.controller;

import com.spunit.ShoppingCartManagement.model.ShoppingCart;
import com.spunit.UserManagement.model.AuthUser;
import com.spunit.ShoppingCartManagement.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    @GetMapping("/cart")
    public ResponseEntity<ShoppingCart> getCart(@AuthenticationPrincipal AuthUser authUser) {
        ShoppingCart cart = cartService.getCartByUser(authUser);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<ShoppingCart> addProduct(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long productId) {
        ShoppingCart cart = cartService.addProductToCart(authUser, productId);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ShoppingCart> updateQuantity(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long productId, @RequestParam int quantity) {
        ShoppingCart cart = cartService.updateProductQuantity(authUser, productId, quantity);
        return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<ShoppingCart> removeProduct(@AuthenticationPrincipal AuthUser authUser, @PathVariable Long productId) {
        ShoppingCart cart = cartService.removeProductFromCart(authUser, productId);
        return ResponseEntity.ok(cart);
    }
}

