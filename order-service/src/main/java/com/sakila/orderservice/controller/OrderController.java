package com.sakila.orderservice.controller;


import com.sakila.orderservice.dto.OrderRequest;
import com.sakila.orderservice.dto.OrderResponse;
import com.sakila.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            orderService.placeOrder(orderRequest);
            return "Order Place Successfully";
        } catch (Exception e) {
            log.error("Error al realizar el pedido: {}", e.getMessage());
            return "Error al realizar el pedido";
        }
    }

    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }
}