package com.valkyrie.order_service.controller;

import com.valkyrie.order_service.model.Order;
import com.valkyrie.order_service.model.OrderWrapper;
import com.valkyrie.order_service.model.Store;
import com.valkyrie.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService service;
    @Autowired
    private void setService(OrderService service) {this.service = service;}

    @PostMapping("/save-order")
    public ResponseEntity<String> save(@RequestBody Order order,
                                       @RequestParam String token) {
        Store<String> store = service.save("save", order, token);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/update-order")
    public ResponseEntity<String> update(@RequestBody Order order,
                                         @RequestParam String token) {
        Store<String> store = service.save("update", order, token);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-order-by-id")
    public ResponseEntity<List<OrderWrapper>> findOrder(@RequestParam String token) {
        Store<List<OrderWrapper>> store = service.getOrderDetails(token);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/cancel-order")
    public ResponseEntity<String> cancelOrder(@RequestParam String id) {
        Store<String> store = service.deleteOrderById(id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }
}
