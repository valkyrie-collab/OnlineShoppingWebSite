package com.valkyrie.product_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valkyrie.product_service.model.Image;
import com.valkyrie.product_service.model.Product;
import com.valkyrie.product_service.model.ProductWrapper;
import com.valkyrie.product_service.model.Store;
import com.valkyrie.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService service;
    @Autowired
    private void setService(ProductService service) {this.service = service;}

    @PostMapping("/save-product")
    public ResponseEntity<String> save(@RequestPart List<MultipartFile> imageFiles,
                                       @RequestParam String productString,
                                       @RequestParam String token) throws IOException {
        Product product = new ObjectMapper().readValue(productString, Product.class);
        List<Image> imageList = new ArrayList<>();

        for (MultipartFile imageFile: imageFiles) {
            Image image = new Image().setName(imageFile.getOriginalFilename())
                    .setType(imageFile.getContentType()).setData(imageFile.getBytes());
            imageList.add(image);
        }
        Store<String> store = service.save("save", product, imageList, token);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/update-product")
    public ResponseEntity<String> update(@RequestParam String productString,
                                         @RequestParam String token) throws IOException {
        Product product = new ObjectMapper().readValue(productString, Product.class);
        Store<String> store = service.save("update", product, null, token);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/update_quantity")
    public ResponseEntity<String> updateQuantity(@RequestParam int quantity,
                                                 @RequestParam String productId) {
        Store<String> store = service.updateQuantity(quantity, productId);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-product-by-id")
    public ResponseEntity<ProductWrapper> findProductById(@RequestParam String id) {
        Store<ProductWrapper> store = service.findProductById(id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-products-by-seller-id")
    public ResponseEntity<List<ProductWrapper>> findProductBySellerId(@RequestParam String token) {
        Store<List<ProductWrapper>> store = service.findProductBySellerId(token);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-products-by-brand")
    public ResponseEntity<List<ProductWrapper>> findProductByBrand(@RequestParam String brand) {
        Store<List<ProductWrapper>> store = service.findProductByBrand(brand);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-products-by-name")
    public ResponseEntity<List<ProductWrapper>> findProductByName(@RequestParam String name) {
        Store<List<ProductWrapper>> store = service.findProductByName(name);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/delete-product-by-id")
    public ResponseEntity<String> removeProductById(@RequestParam String id) {
        id = Base64.getEncoder().encodeToString(id.getBytes());
        Store<String> store = service.deleteProductById(id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/delete-products-by-seller-id")
    public ResponseEntity<String> removeProductBySellerId(@RequestParam String sellerId) {
        sellerId = Base64.getEncoder().encodeToString(sellerId.getBytes());
        Store<String> store = service.deleteProductsBySellerId(sellerId);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }
}
