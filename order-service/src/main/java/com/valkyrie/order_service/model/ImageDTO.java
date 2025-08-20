package com.valkyrie.order_service.model;

import java.util.Base64;

public class ImageDTO {
    private String name;
    private String type;
    private String encodedByteData;

    public String getName() {return name;}

    public String getType() {return type;}

    public String getEncodedByteData() {return encodedByteData;}

    public ImageDTO setName(String name) {
        this.name = name;
        return this;
    }

    public ImageDTO setType(String type) {
        this.type = type;
        return this;
    }

    public ImageDTO setEncodedByteData(byte[] data) {
        encodedByteData = Base64.getEncoder().encodeToString(data);
        return this;
    }
}
