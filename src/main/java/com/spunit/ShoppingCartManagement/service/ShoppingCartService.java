package com.spunit.ShoppingCartManagement.service;

import com.spunit.ShoppingCartManagement.model.ShoppingCart;
import com.spunit.UserManagement.model.AuthUser;
import com.spunit.ShoppingCartManagement.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository cartRepository;

    public ShoppingCart getCartByUser(AuthUser authUser) {
        return cartRepository.findByAuthUser(authUser);
    }

    public ShoppingCart addProductToCart(AuthUser authUser, Long productId) {
        ShoppingCart cart = getCartByUser(authUser);
        if (cart == null) {
            cart = new ShoppingCart();
            cart.setAuthUser(authUser);
        }
        cart.getProducts().put(productId, cart.getProducts().getOrDefault(productId, 0) + 1);
        return cartRepository.save(cart);
    }

    public ShoppingCart updateProductQuantity(AuthUser authUser, Long productId, int quantity) {
        ShoppingCart cart = getCartByUser(authUser);
        if (cart != null && cart.getProducts().containsKey(productId)) {
            if (quantity <= 0) {
                cart.getProducts().remove(productId);
            } else {
                cart.getProducts().put(productId, quantity);
            }
            return cartRepository.save(cart);
        }
        return null;
    }

    public ShoppingCart removeProductFromCart(AuthUser authUser, Long productId) {
        ShoppingCart cart = getCartByUser(authUser);
        if (cart != null) {
            cart.getProducts().remove(productId);
            return cartRepository.save(cart);
        }
        return null;
    }
}

