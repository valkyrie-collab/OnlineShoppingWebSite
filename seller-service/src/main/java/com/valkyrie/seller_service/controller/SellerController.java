package com.valkyrie.seller_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valkyrie.seller_service.model.*;
import com.valkyrie.seller_service.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/seller")
public class SellerController {
    private SellerService service;
    @Autowired
    private void setService(SellerService service) {this.service = service;}

    private Seller getSellerFromText(String seller) throws IOException {
        return new ObjectMapper().readValue(seller, Seller.class);
    }

    @PostMapping("/save-seller")
    public ResponseEntity<String> save(@RequestPart MultipartFile imageFile,
                                       @RequestPart MultipartFile documentFile,
                                       @RequestParam String sellerString,
                                       @RequestParam String token) throws IOException {
        Seller seller = getSellerFromText(sellerString);
        Image image = new Image().setName(imageFile.getName())
                .setType(imageFile.getContentType()).setData(imageFile.getBytes());

        Documents documents = new Documents().setName(documentFile.getName())
                .setType(documentFile.getContentType()).setData(documentFile.getBytes());

        seller = seller.setImage(image).setDocuments(documents);

        Store<String> store = service.save("save", token, seller);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/update-seller")
    public ResponseEntity<String> update(@RequestPart MultipartFile imageFile,
                                         @RequestPart MultipartFile documentFile,
                                         @RequestParam String sellerString,
                                         @RequestParam String token) throws IOException {
        Seller seller = getSellerFromText(sellerString);
        Image image = new Image().setName(imageFile.getName())
                .setType(imageFile.getContentType()).setData(imageFile.getBytes());

        Documents documents = new Documents().setName(documentFile.getName())
                .setType(documentFile.getContentType()).setData(documentFile.getBytes());

        seller = seller.setImage(image).setDocuments(documents);

        Store<String> store = service.save("update", token, seller);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-seller-by-id")
    public ResponseEntity<SellerWrapper> findById(@RequestParam String token) {
        Store<SellerWrapper> store = service.findSellerById(token);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/delete-seller-by-id")
    public ResponseEntity<String> deleteById(@RequestParam(required = false) String token,
                                             @RequestParam(required = false) String id) {
        Store<String> store = service.deleteSellerById(token, id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }
}
