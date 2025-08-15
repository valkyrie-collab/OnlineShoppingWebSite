package com.valkyrie.seller_service.controller;

import com.valkyrie.seller_service.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    private SellerService service;
    @Autowired
    private void setService(SellerService service) {this.service = service;}
}
