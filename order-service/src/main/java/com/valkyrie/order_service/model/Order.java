package com.valkyrie.order_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String id;
    private String customerId;
    private String productId;
    private int quantity;
    private Date orderDate;

    public String getId() {return id;}

    public String getCustomerId() {return customerId;}

    public String getProductId() {return productId;}

    public int getQuantity() {return quantity;}

    public Date getOrderDate() {return orderDate;}

    public Order setId(String id) {
        this.id = id;
        return this;
    }

    public Order setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public Order setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public Order setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Order setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    @Override
    public String toString() {
        return id + customerId + productId + quantity + orderDate;
    }
}
