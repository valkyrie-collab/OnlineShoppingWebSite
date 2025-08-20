package com.valkyrie.seller_service.model;

import java.util.Base64;

public class DocumentDTO {
    private String name;
    private String type;
    private String encodedByteData;

    public String getName() {return name;}

    public String getType() {return type;}

    public String getEncodedByteData() {return encodedByteData;}

    public DocumentDTO setName(String name) {
        this.name = name;
        return this;
    }

    public DocumentDTO setType(String type) {
        this.type = type;
        return this;
    }

    public DocumentDTO setEncodedByteData(byte[] data) {
        encodedByteData = Base64.getEncoder().encodeToString(data);
        return this;
    }
}
