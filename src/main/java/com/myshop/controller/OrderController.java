package com.myshop.controller;

import com.myshop.entity.OrderItem;
import com.myshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderDto.OrderRequest request) {
        List<OrderItem> items = request.getItems().stream().map(i -> {
            OrderItem oi = new OrderItem();
            oi.setProduct(i.toProduct());
            oi.setQuantity(i.getQuantity());
            return oi;
        }).collect(Collectors.toList());

        return orderService.createOrder(request.getUserId(), items);
    }
}