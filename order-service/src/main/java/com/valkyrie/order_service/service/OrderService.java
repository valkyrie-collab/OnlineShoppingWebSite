package com.valkyrie.order_service.service;

import com.valkyrie.order_service.config.TokenConfig;
import com.valkyrie.order_service.feign.ProductFeignController;
import com.valkyrie.order_service.model.Order;
import com.valkyrie.order_service.model.OrderWrapper;
import com.valkyrie.order_service.model.ProductWrapper;
import com.valkyrie.order_service.model.Store;
import com.valkyrie.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private OrderRepository repo;
    @Autowired
    private void setRepo(OrderRepository repo) {this.repo = repo;}

    private TokenConfig config;
    @Autowired
    private void setConfig(TokenConfig config) {this.config = config;}

    private ProductFeignController feign;
    @Autowired
    private void setFeign(ProductFeignController feign) {this.feign = feign;}

    public Store<String> save(String operation, Order order, String token) {
        String username = config.getUsername(token);
        order = order.setCustomerId(username);
        boolean check = operation.equals("save");

        if (check) {
            String uuid = UUID.randomUUID().toString();

            if (!feign.updateQuantity(order.getQuantity(), order.getProductId())
                    .getStatusCode().equals(HttpStatusCode.valueOf(202))) {
                return Store.initialize(HttpStatus.OK, "Product update error...");
            }

            ProductWrapper wrapper = feign.findProductById(order.getProductId()).getBody();

            if (wrapper == null) {return Store.initialize(HttpStatus.BAD_REQUEST, "product empty...");}

            repo.save(order.setId(uuid));
            return Store.initialize(HttpStatus.OK,
                    "The order with ID = " + uuid + " has been placed successfully.....");
        } else {
            Order presentOrder = repo.findById(order.getId()).orElse(order);

            if (!order.toString().equals(presentOrder.toString())) {
                repo.save(order);
                return Store.initialize(HttpStatus.OK,
                      "The order with ID = " + order.getId() + " has been updated successfully.....");
            } else {
                return Store.initialize(HttpStatus.BAD_REQUEST, "order not updated..");
            }

        }
    }

    public Store<List<OrderWrapper>> getOrderDetails(String token) {
        String customerId = config.getUsername(token);
        List<Order> orders = repo.findAllByCustomerId(customerId);
        List<OrderWrapper> wrappers = new ArrayList<>();

        if (orders.isEmpty()) {return Store.initialize(HttpStatus.BAD_REQUEST, new ArrayList<>());}

        for (Order order : orders) {
            ProductWrapper product = feign.findProductById(order.getProductId()).getBody();

            if (product == null) {return Store.initialize(HttpStatus.BAD_REQUEST, new ArrayList<>());}

            wrappers.add(new OrderWrapper().setId(order.getId()).setProduct(product)
                    .setOrderDate(order.getOrderDate()).setCustomerId(order.getCustomerId())
                    .setQuantity(order.getQuantity()));
        }

        return Store.initialize(HttpStatus.OK, wrappers);
    }

    @Transactional
    public Store<String> deleteOrderById(String id) {
        id = new String(Base64.getDecoder().decode(id));
        Order presentOrder = repo.findById(id).orElse(null);

        if (presentOrder == null) {
            return Store.initialize(HttpStatus.OK, "The Order is Already canceled....");
        }

        if (!feign.updateQuantity((-presentOrder.getQuantity()), presentOrder.getProductId())
                .getStatusCode().equals(HttpStatusCode.valueOf(202))) {
            return Store.initialize(HttpStatus.OK, "Product update error...");
        }

        repo.deleteById(id);

        return repo.findById(id).orElse(null) == null?
                Store.initialize(HttpStatus.OK, "Deletion successful..") :
                Store.initialize(HttpStatus.BAD_REQUEST, "Deletion is unsuccessful....");
    }
}
