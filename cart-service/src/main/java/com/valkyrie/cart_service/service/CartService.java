package com.valkyrie.cart_service.service;

import com.valkyrie.cart_service.config.TokenConfig;
import com.valkyrie.cart_service.feign.ProductFeignController;
import com.valkyrie.cart_service.model.*;
import com.valkyrie.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {
    private CartRepository repo;
    @Autowired
    private void setRepo(CartRepository repo) {this.repo = repo;}

    private TokenConfig config;
    @Autowired
    private void setConfig(TokenConfig config) {this.config = config;}

    private ProductFeignController feign;
    @Autowired
    private void setFeign(ProductFeignController feign) {this.feign = feign;}

    public Store<String> save(String token, String productId) {
        String username = config.getUsername(token);
        Cart validCart = repo.findById(username).orElse(null);

        if (validCart != null) {
            List<String> productIds = validCart.getProductIds(); productIds.add(productId);
            repo.save(validCart.setProductIds(productIds));
            return Store.initialize(HttpStatus.OK, "The Product has been added successfully....");
        }

        repo.save(
                new Cart().setCustomerId(username).setProductIds(List.of(productId))
        );

        return Store.initialize(HttpStatus.OK, "Added to cart successfully......");
    }

    @Transactional
    public Store<CartWrapper> find(String token) {
        String username = config.getUsername(token);
        Cart cart = repo.findByCustomerId(username);
        CartWrapper wrapper = new CartWrapper().setCustomerId(username)
                .setProducts(cart.getProductIds().stream().map(
                        p -> feign.findProductById(p).getBody()).toList()
                );

        return Store.initialize(HttpStatus.OK, wrapper);
    }

    public Store<String> delete(String id) {
        id = new String(Base64.getDecoder().decode(id));

        if (repo.findById(id).orElse(null) == null) {
            return Store.initialize(HttpStatus.OK, "There is no such to remove.....");
        }

        repo.deleteById(id);

        return repo.findById(id).orElse(null) == null?
                Store.initialize(HttpStatus.OK, "Item removed...") :
                Store.initialize(HttpStatus.BAD_REQUEST, "Err occurred during deletion.....");
    }
}
