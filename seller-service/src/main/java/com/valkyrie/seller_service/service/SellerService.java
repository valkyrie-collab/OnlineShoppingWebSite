package com.valkyrie.seller_service.service;

import com.valkyrie.seller_service.config.TokenConfig;
import com.valkyrie.seller_service.feign.ProductFeignController;
import com.valkyrie.seller_service.model.*;
import com.valkyrie.seller_service.repository.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SellerService {
    private SellerRepo repo;
    @Autowired
    private void setRepo(SellerRepo repo) {this.repo = repo;}

    private TokenConfig config;
    @Autowired
    private void setConfig(TokenConfig config) {this.config = config;}

    private ProductFeignController feign;
    @Autowired
    private void setFeign(ProductFeignController feign) {this.feign = feign;}

    @Transactional
    public Store<String> save(String operation, String token, Seller seller) {
        boolean check = operation.equals("save");
        String username = config.getUsername(token);
        seller = seller.setId(username);

        if (check) {
            repo.save(seller);
            return Store.initialize(HttpStatus.ACCEPTED,
                    "The seller data has been saved successfully.....");
        } else {
            Seller presentSeller = repo.findById(username).orElse(seller);

            if (!seller.toString().equals(presentSeller.toString())) {
                repo.save(seller.setImage(presentSeller.getImage()).setDocuments(presentSeller.getDocuments()));
                return Store.initialize(HttpStatus.ACCEPTED, "The seller data has been updated...");
            }
            else {
                return Store.initialize(HttpStatus.BAD_REQUEST, "The seller is not updated...");
            }
        }

    }

    @Transactional
    public Store<SellerWrapper> findSellerById(String token) {
        String username = config.getUsername(token);
        Seller seller = null; SellerWrapper wrapper = null;

        if (!username.isEmpty()) {
            seller = repo.findById(username).orElse(null);

            if (seller != null) {
                List<ProductWrapper> products = feign.findProductBySellerId(username).getBody();
                Image image = seller.getImage(); Documents documents = seller.getDocuments();
                DocumentDTO documentDTO = new DocumentDTO().setName(documents.getName())
                        .setType(documents.getType()).setEncodedByteData(documents.getData());
                ImageDTO imageDTO = new ImageDTO().setName(image.getName())
                        .setType(image.getType()).setEncodedByteData(image.getData());
                wrapper = new SellerWrapper().setId(seller.getId()).setAddress(seller.getAddress())
                        .setEmail(seller.getEmail()).setBusiness(seller.getBusiness())
                        .setGstNumber(seller.getGstNumber()).setBankAccountDetails(seller.getBankAccountDetails())
                        .setDocuments(documentDTO).setPhoneNumber(seller.getPhoneNumber())
                        .setName(seller.getName()).setDescription(seller.getDescription())
                        .setImage(imageDTO).setStatus(seller.getStatus()).setRating(seller.getRating())
                        .setRegistrationDate(seller.getRegistrationDate()).setProductList(products);

                return Store.initialize(HttpStatus.OK, wrapper);
            }

        }

        return Store.initialize(HttpStatus.BAD_REQUEST, wrapper);
    }

    public Store<String> deleteSellerById(String token, String id) {
        String username = null;

        if (id == null) {
            username = config.getUsername(token);
        } else {
            username = id;
        }

        if (repo.findById(username).orElse(null) == null) {
            return Store.initialize(HttpStatus.OK,
                    "The Seller with username = " + username + " has already been delete or not present");
        }

        repo.deleteById(username);

        return Store.initialize(HttpStatus.OK,
                "The Seller with username = " + username + " has been deleted successfully");
    }
}
