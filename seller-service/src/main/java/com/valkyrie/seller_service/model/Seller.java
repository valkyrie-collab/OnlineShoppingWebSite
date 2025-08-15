package com.valkyrie.seller_service.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "seller")
public class Seller {
    @Id
    private String id;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private float contactNumber;
    @ElementCollection
    private List<String> productIds;
    private int rating;
    private LocalDate dob;
    private LocalDate dateOfJoin;
    @Embedded
    private Image image;

    public String getId() {return id;}

    public String getEmail() {return email;}

    public String getFirstName() {return firstName;}

    public String getMiddleName() {return middleName;}

    public String getLastName() {return lastName;}

    public float getContactNumber() {return contactNumber;}

    public List<String> getProductIds() {return productIds;}

    public int getRating() {return rating;}

    public LocalDate getDob() {return dob;}

    public LocalDate getDateOfJoin() {return dateOfJoin;}

    public Image getImage() {return image;}

    public Seller setId(String id) {
        this.id = id;
        return this;
    }

    public Seller setEmail(String email) {
        this.email = email;
        return this;
    }

    public Seller setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Seller setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public Seller setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Seller setContactNumber(float contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public Seller setProductIds(List<String> productIds) {
        this.productIds = productIds;
        return this;
    }

    public Seller setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public Seller setDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public Seller setDateOfJoin(LocalDate dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
        return this;
    }

    public Seller setImage(Image image) {
        this.image = image;
        return this;
    }

    @Override
    public String toString() {
        return id + email + firstName + middleName +
                lastName + contactNumber + rating + dob + dateOfJoin;
    }
}
