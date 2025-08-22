package com.valkyrie.cart_service.model;

import java.util.List;

public class CartWrapper {
//    private String id;
    private String customerId;
    private List<ProductWrapper> products;

//    public String getId() {return id;}

    public String getCustomerId() {return customerId;}

    public List<ProductWrapper> getProducts() {return products;}

//    public CartWrapper setId(String id) {
//        this.id = id;
//        return this;
//    }

    public CartWrapper setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public CartWrapper setProducts(List<ProductWrapper> products) {
        this.products = products;
        return this;
    }
}
