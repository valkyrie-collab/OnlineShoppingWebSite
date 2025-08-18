package com.valkyrie.product_service.repository;

import com.valkyrie.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllBySellerId(String sellerId);

    List<Product> findAllBySearchKeyword(String searchKeyword);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:name%")
    List<Product> findAllByName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE LOWER(p.brand) = :brand")
    List<Product> findAllByBrand(@Param("brand") String brand);

    void deleteAllBySellerId(String sellerId);
}
