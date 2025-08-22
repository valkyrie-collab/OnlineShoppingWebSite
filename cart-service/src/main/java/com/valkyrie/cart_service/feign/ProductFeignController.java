package com.valkyrie.cart_service.feign;

import com.valkyrie.cart_service.model.ProductWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("PRODUCT-SERVICE")
public interface ProductFeignController {
    @GetMapping("/product/find-product-by-id")
    ResponseEntity<ProductWrapper> findProductById(@RequestParam String id);
}
