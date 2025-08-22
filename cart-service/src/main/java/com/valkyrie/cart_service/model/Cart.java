package com.valkyrie.cart_service.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
//    private String id;
    private String customerId;
    @ElementCollection
    private List<String> productIds;

//    public String getId() {return id;}

    public List<String> getProductIds() {return productIds;}

    public String getCustomerId() {return customerId;}

//    public Cart setId(String id) {
//        this.id = id;
//        return this;
//    }

    public Cart setProductIds(List<String> productIds) {
        this.productIds = productIds;
        return this;
    }

    public Cart setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    @Override
    public String toString() {return customerId;}
}
