package com.valkyrie.cart_service.controller;

import com.valkyrie.cart_service.model.CartWrapper;
import com.valkyrie.cart_service.model.Store;
import com.valkyrie.cart_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService service;
    @Autowired
    private void setService(CartService service) {this.service = service;}

    @PostMapping("/save-cart")
    public ResponseEntity<String> save(@RequestParam String token,
                                       @RequestParam String productId) {
        Store<String> store = service.save(token, productId);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-cart-by-id")
    public ResponseEntity<CartWrapper> findById(@RequestParam String token) {
        Store<CartWrapper> store = service.find(token);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/remove-item-from-cart")
    public ResponseEntity<String> deleteById(@RequestParam String id) {
        id = Base64.getEncoder().encodeToString(id.getBytes());
        Store<String> store = service.delete(id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }
}
