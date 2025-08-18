package com.valkyrie.product_service.controller;

import com.valkyrie.product_service.model.Product;
import com.valkyrie.product_service.model.ProductWrapper;
import com.valkyrie.product_service.model.Store;
import com.valkyrie.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService service;
    @Autowired
    private void setService(ProductService service) {this.service = service;}

    @PostMapping("/save-product")
    public ResponseEntity<String> save(@RequestBody Product product) {
        Store<String> store = service.save("save", product);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/update-product")
    public ResponseEntity<String> update(@RequestBody Product product) {
        Store<String> store = service.save("update", product);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-product-by-id")
    public ResponseEntity<ProductWrapper> findProductById(@RequestParam String id) {
        Store<ProductWrapper> store = service.findProductById(id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-products-by-seller-id")
    public ResponseEntity<List<ProductWrapper>> findProductBySellerId(@RequestParam String sellerId) {
        Store<List<ProductWrapper>> store = service.findProductBySellerId(sellerId);

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
        Store<String> store = service.deleteProductById(id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/delete-products-by-seller-id")
    public ResponseEntity<String> removeProductBySellerId(@RequestParam String sellerId) {
        Store<String> store = service.deleteProductsBySellerId(sellerId);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }
}
