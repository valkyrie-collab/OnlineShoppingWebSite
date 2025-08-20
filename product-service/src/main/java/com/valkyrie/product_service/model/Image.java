package com.valkyrie.product_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    @Lob
    @JsonIgnore
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id") // foreign key column in image table
    private Product product;


    public String getName() {return name;}

    public String getType() {return type;}

    public byte[] getData() {return data;}

    public Product getProduct() {return product;}

    public Image setName(String name) {
        this.name = name;
        return this;
    }

    public Image setType(String type) {
        this.type = type;
        return this;
    }

    public Image setData(byte[] data) {
        this.data = data;
        return this;
    }

    public Image setProduct(Product product) {
        this.product = product;
        return this;
    }

    @Override
    public String toString() {return name + "." + type;}
}
