package com.valkyrie.seller_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "document")
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    @Lob
    private byte[] data;

    public String getName() {return name;}

    public String getType() {return type;}

    public byte[] getData() {return data;}

    public Documents setName(String name) {
        this.name = name;
        return this;
    }

    public Documents setType(String type) {
        this.type = type;
        return this;
    }

    public Documents setData(byte[] data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {return name + "." + type;}
}
