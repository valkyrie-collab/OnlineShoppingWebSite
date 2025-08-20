package com.valkyrie.order_service.feign;

import com.valkyrie.order_service.model.ProductWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@FeignClient("PRODUCT-SERVICE")
public interface ProductFeignController {

    @GetMapping("/product/find-product-by-id")
    ResponseEntity<ProductWrapper> findProductById(@RequestParam String id);

    @PostMapping("/product/update-product")
    ResponseEntity<String> update(@RequestParam String productString,
                                         @RequestParam String token) throws IOException;
}
