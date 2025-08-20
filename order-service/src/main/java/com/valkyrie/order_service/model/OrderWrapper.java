package com.valkyrie.order_service.model;

import java.util.Date;

public class OrderWrapper {
    private String id;
    private String customerId;
    private int quantity;
    private Date orderDate;
    private ProductWrapper product;

    public String getId() {return id;}

    public String getCustomerId() {return customerId;}

    public int getQuantity() {return quantity;}

    public Date getOrderDate() {return orderDate;}

    public ProductWrapper getProduct() {return product;}

    public OrderWrapper setId(String id) {
        this.id = id;
        return this;
    }

    public OrderWrapper setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderWrapper setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderWrapper setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public OrderWrapper setProduct(ProductWrapper product) {
        this.product = product;
        return this;
    }
}
