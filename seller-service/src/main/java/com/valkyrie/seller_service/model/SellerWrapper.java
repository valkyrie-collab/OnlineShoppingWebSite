package com.valkyrie.seller_service.model;

import java.util.Date;
import java.util.List;

public class SellerWrapper {
    private String id;
    private String name;
    private String email;
    private long phoneNumber;
    private String address;
    private String business;
    private String gstNumber;
    private Date registrationDate;
    private ImageDTO image;
    private String bankAccountDetails;
    private String status;
    private int rating;
    private DocumentDTO documents;
    private String description;
    private List<ProductWrapper> productList;

    public String getId() {return id;}

    public String getName() {return name;}

    public String getEmail() {return email;}

    public String getAddress() {return address;}

    public String getBusiness() {return business;}

    public String getGstNumber() {return gstNumber;}

    public String getBankAccountDetails() {return bankAccountDetails;}

    public String getStatus() {return status;}

    public String getDescription() {return description;}

    public int getRating() {return rating;}

    public long getPhoneNumber() {return phoneNumber;}

    public ImageDTO getImage() {return image;}

    public DocumentDTO getDocuments() {return documents;}

    public Date getRegistrationDate() {return registrationDate;}

    public List<ProductWrapper> getProductList() {return productList;}

    public SellerWrapper setId(String id) {
        this.id = id;
        return this;
    }

    public SellerWrapper setEmail(String email) {
        this.email = email;
        return this;
    }

    public SellerWrapper setName(String name) {
        this.name = name;
        return this;
    }

    public SellerWrapper setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public SellerWrapper setBusiness(String business) {
        this.business = business;
        return this;
    }

    public SellerWrapper setAddress(String address) {
        this.address = address;
        return this;
    }

    public SellerWrapper setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
        return this;
    }

    public SellerWrapper setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public SellerWrapper setImage(ImageDTO image) {
        this.image = image;
        return this;
    }

    public SellerWrapper setBankAccountDetails(String bankAccountDetails) {
        this.bankAccountDetails = bankAccountDetails;
        return this;
    }

    public SellerWrapper setStatus(String status) {
        this.status = status;
        return this;
    }

    public SellerWrapper setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public SellerWrapper setDocuments(DocumentDTO documents) {
        this.documents = documents;
        return this;
    }

    public SellerWrapper setDescription(String description) {
        this.description = description;
        return this;
    }

    public SellerWrapper setProductList(List<ProductWrapper> productList) {
        this.productList = productList;
        return this;
    }
}
