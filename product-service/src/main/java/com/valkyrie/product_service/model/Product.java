package com.valkyrie.product_service.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
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
//    @ElementCollection
    private String sellerId;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images;


    public String getId() {return id;}

    public String getName() {return name;}

    public String getDescription() {return description;}

    public String getCategory() {return category;}

    public int getPrice() {return price;}

    public String getBrand() {return brand;}

    public List<Image> getImages() {return images;}

    public int getQuantity() {return quantity;}

    public String getColor() {return color;}

    public int getSize() {return size;}

    public String getVariant() {return variant;}

    public int getDiscount() {return discount;}

    public String getStatus() {return status;}

    public String getSearchKeyword() {return searchKeyword;}

    public int getRating() {return rating;}

    public String getShippingInformation() {return shippingInformation;}

    public String getSellerId() {return sellerId;}

    public Product setId(String id) {
        this.id = id;
        return this;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Product setPrice(int price) {
        this.price = price;
        return this;
    }

    public Product setCategory(String category) {
        this.category = category;
        return this;
    }

    public Product setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Product setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public Product setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Product setColor(String color) {
        this.color = color;
        return this;
    }

    public Product setSize(int size) {
        this.size = size;
        return this;
    }

    public Product setVariant(String variant) {
        this.variant = variant;
        return this;
    }

    public Product setDiscount(int discount) {
        this.discount = discount;
        return this;
    }

    public Product setStatus(String status) {
        this.status = status;
        return this;
    }

    public Product setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
        return this;
    }

    public Product setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public Product setShippingInformation(String shippingInformation) {
        this.shippingInformation = shippingInformation;
        return this;
    }

    public Product setSellerId(String sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    @Override
    public String toString() {
        return id + name + description + category + price +
                brand + quantity + color + size + variant + discount +
                status + searchKeyword + rating + shippingInformation;
    }
}
