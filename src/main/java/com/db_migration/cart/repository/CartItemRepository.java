package com.db_migration.cart.repository;

import com.db_migration.cart.entity.Cart;
import com.db_migration.cart.entity.CartItem;
import com.db_migration.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
    List<CartItem> findByCart(Cart cart);
}
