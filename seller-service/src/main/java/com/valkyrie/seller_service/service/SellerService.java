package com.valkyrie.seller_service.service;

import com.valkyrie.seller_service.model.Seller;
import com.valkyrie.seller_service.repository.SellerRepo;
import com.valkyrie.seller_service.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    private SellerRepo repo;
    @Autowired
    private void setRepo(SellerRepo repo) {this.repo = repo;}

    public Store<String> save(String operation, String token, Seller seller) {
        boolean check = operation.equals("save");

        if (check) {
            repo.save(seller);
        } else {
            repo.save(seller);
        }

    }
}
