package com.valkyrie.product_service.repository;

import com.valkyrie.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllBySellerId(String sellerId);

    @Query("SELECT p FROM Product p WHERE LOWER(p.searchKeyword) LIKE %:substring%")
    List<Product> findAllBySearchKeyword(@Param("substring") String substring);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:name%")
    List<Product> findAllByName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE LOWER(p.brand) = :brand")
    List<Product> findAllByBrand(@Param("brand") String brand);

    @Modifying
    @Query("UPDATE Product p SET p.rating = :rating WHERE p.id = :id")
    Integer updateProductRating(@Param("id") String id, @Param("rating") String rating);

    @Query("SELECT p.rating FROM Product p WHERE p.id = :id")
    Integer findProductRatingById(@Param("id") String id);

    @Modifying
    @Query("UPDATE Product p SET p.quantity = :quantity WHERE p.id = :id")
    void updateProductQuantity(@Param("id") String id, @Param("quantity") int quantity);

    @Query("SELECT p.quantity FROM Product p WHERE p.id = :id")
    Integer findProductQuantityById(@Param("id") String id);


    void deleteAllBySellerId(String sellerId);
}
