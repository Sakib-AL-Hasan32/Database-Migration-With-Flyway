package com.db_migration.cart.repository;

import com.db_migration.auth.entity.User;
import com.db_migration.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUser(User user);
}
