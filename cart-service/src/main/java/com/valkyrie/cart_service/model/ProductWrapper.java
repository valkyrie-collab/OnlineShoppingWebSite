package com.valkyrie.cart_service.model;

import java.util.List;

public class ProductWrapper {
    private String id;
    private String name;
    private String description;
    private String category;
    private int price;
    private String brand;
    private int quantity;
    private String color;
    private int size;
    private String variant;
    private int discount;
    private String status;
    private String searchKeyword;
    private int rating;
    private String shippingInformation;
    private List<ImageDTO> images;

    public String getId() {return id;}

    public String getName() {return name;}

    public String getDescription() {return description;}

    public String getCategory() {return category;}

    public int getPrice() {return price;}

    public String getBrand() {return brand;}

    public List<ImageDTO> getImage() {return images;}

    public int getQuantity() {return quantity;}

    public String getColor() {return color;}

    public int getSize() {return size;}

    public String getVariant() {return variant;}

    public int getDiscount() {return discount;}

    public String getStatus() {return status;}

    public String getSearchKeyword() {return searchKeyword;}

    public int getRating() {return rating;}

    public String getShippingInformation() {return shippingInformation;}

    public ProductWrapper setId(String id) {
        this.id = id;
        return this;
    }

    public ProductWrapper setName(String name) {
        this.name = name;
        return this;
    }

    public ProductWrapper setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductWrapper setPrice(int price) {
        this.price = price;
        return this;
    }

    public ProductWrapper setCategory(String category) {
        this.category = category;
        return this;
    }

    public ProductWrapper setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ProductWrapper setImage(List<ImageDTO> images) {
        this.images = images;
        return this;
    }

    public ProductWrapper setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductWrapper setColor(String color) {
        this.color = color;
        return this;
    }

    public ProductWrapper setSize(int size) {
        this.size = size;
        return this;
    }

    public ProductWrapper setVariant(String variant) {
        this.variant = variant;
        return this;
    }

    public ProductWrapper setDiscount(int discount) {
        this.discount = discount;
        return this;
    }

    public ProductWrapper setStatus(String status) {
        this.status = status;
        return this;
    }

    public ProductWrapper setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
        return this;
    }

    public ProductWrapper setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public ProductWrapper setShippingInformation(String shippingInformation) {
        this.shippingInformation = shippingInformation;
        return this;
    }
}
