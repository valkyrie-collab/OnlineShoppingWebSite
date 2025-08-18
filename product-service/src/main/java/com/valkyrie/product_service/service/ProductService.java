package com.valkyrie.product_service.service;

import com.valkyrie.product_service.model.Product;
import com.valkyrie.product_service.model.ProductWrapper;
import com.valkyrie.product_service.model.Store;
import com.valkyrie.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private ProductRepository repo;
    @Autowired
    private void setRepo(ProductRepository repo) {this.repo = repo;}

    private ProductWrapper getProductWrapper(Product product) {
        return new ProductWrapper().setBrand(product.getBrand())
                .setCategory(product.getCategory()).setColor(product.getColor())
                .setDescription(product.getDescription()).setDiscount(product.getDiscount())
                .setImage(product.getImage()).setName(product.getName()).setPrice(product.getPrice())
                .setQuantity(product.getQuantity()).setRating(product.getRating()).setSize(product.getSize())
                .setVariant(product.getVariant()).setSearchKeyword(product.getSearchKeyword())
                .setStatus(product.getStatus()).setShippingInformation(product.getShippingInformation());
    }

    public Store<String> save(String operation, Product product) {
        boolean check = operation.equals("save");

        if (check) {
            String uuid = UUID.randomUUID().toString();
            repo.save(product.setId(uuid));

            return Store.initialize(HttpStatus.ACCEPTED,
                    "The product " + product.getName() + " saved with id = " + uuid);
        } else {

            if (!product.toString().equals(repo.findById(product.getId()).orElse(product).toString())) {
                repo.save(product);

                return Store.initialize(HttpStatus.ACCEPTED,
                        "The product " + product.getName() + " updated with id = " + product.getId());
            } else {

                return Store.initialize(HttpStatus.BAD_REQUEST, "The product not updated....");
            }

        }

    }

    @Transactional
    public Store<ProductWrapper> findProductById(String id) {
        Product product = repo.findById(id).orElse(null);
        ProductWrapper wrapper = null;

        if (product == null) {return Store.initialize(HttpStatus.NOT_FOUND, wrapper);}

        wrapper = getProductWrapper(product);

        return Store.initialize(HttpStatus.OK, wrapper);
    }

    @Transactional
    public Store<List<ProductWrapper>> findProductBySellerId(String sellerId) {
        List<Product> products = repo.findAllBySellerId(sellerId);
        List<ProductWrapper> wrappers = new LinkedList<>();

        if (products == null || products.isEmpty()) {return Store.initialize(HttpStatus.NOT_FOUND, wrappers);}

        for (Product product : products) {
            wrappers.add(getProductWrapper(product));
        }

        return Store.initialize(HttpStatus.OK, wrappers);
    }

    @Transactional
    public Store<List<ProductWrapper>> findProductBySearchKeyword(String searchKeyword) {
        List<Product> products = repo.findAllBySearchKeyword(searchKeyword);
        List<ProductWrapper> wrappers = new LinkedList<>();

        if (products == null || products.isEmpty()) {return Store.initialize(HttpStatus.NOT_FOUND, wrappers);}

        for (Product product : products) {
            wrappers.add(getProductWrapper(product));
        }

        return Store.initialize(HttpStatus.OK, wrappers);
    }

    @Transactional
    public Store<List<ProductWrapper>> findProductByName(String name) {
        List<Product> products = repo.findAllByName(name.toLowerCase());
        List<ProductWrapper> wrappers = new LinkedList<>();

        if (products == null || products.isEmpty()) {return Store.initialize(HttpStatus.NOT_FOUND, wrappers);}

        for (Product product : products) {
            wrappers.add(getProductWrapper(product));
        }

        return Store.initialize(HttpStatus.OK, wrappers);
    }

    @Transactional
    public Store<List<ProductWrapper>> findProductByBrand(String brand) {
        List<Product> products = repo.findAllByBrand(brand.toLowerCase());
        List<ProductWrapper> wrappers = new LinkedList<>();

        if (products == null || products.isEmpty()) {return Store.initialize(HttpStatus.NOT_FOUND, wrappers);}

        for (Product product : products) {
            wrappers.add(getProductWrapper(product));
        }

        return Store.initialize(HttpStatus.OK, wrappers);
    }

    public Store<String> deleteProductById(String id) {

        if (repo.findById(id).orElse(null) == null) {
            return Store.initialize(HttpStatus.OK,
                    "The product with id = " + id + " has already been delete successfully...");
        }

        repo.deleteById(id);

        return Store.initialize(HttpStatus.OK,
                "The product with id = " + id + " has been deleted successfully....");
    }

    @Transactional
    public Store<String> deleteProductsBySellerId(String sellerId) {

        if (repo.findAllBySellerId(sellerId).isEmpty()) {
            return Store.initialize(HttpStatus.OK,
                    "The products with id = " + sellerId + " has already been delete successfully...");
        }

        repo.deleteAllBySellerId(sellerId);

        return Store.initialize(HttpStatus.OK,
                "The Products with id = " + sellerId + " has been delete successfully...");
    }
}
