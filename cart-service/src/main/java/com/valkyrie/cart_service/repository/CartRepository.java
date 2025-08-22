package com.valkyrie.cart_service.repository;

import com.valkyrie.cart_service.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByCustomerId(String customerId);
}
