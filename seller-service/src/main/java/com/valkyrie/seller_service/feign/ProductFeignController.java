package com.valkyrie.seller_service.feign;

import com.valkyrie.seller_service.model.ProductWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("PRODUCT-SERVICE")
public interface ProductFeignController {
    @GetMapping("/product/find-products-by-seller-id")
    ResponseEntity<List<ProductWrapper>> findProductBySellerId(@RequestParam String sellerId);
}
