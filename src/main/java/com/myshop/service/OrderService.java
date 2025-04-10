package com.myshop.service;

import com.myshop.entity.Order;
import com.myshop.entity.OrderItem;
import com.myshop.entity.Product;
import com.myshop.entity.User;
import com.myshop.repository.OrderItemRepository;
import com.myshop.repository.OrderRepository;
import com.myshop.repository.ProductRepository;
import com.myshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public Order createOrder(Long userId, List<OrderItem> items) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));
        Order order = new Order();
        order.setUser(user);
        order.setStatus("READY");
        order = orderRepository.save(order);

        int totalPrice = 0;
        List<OrderItem> savedItems = new ArrayList<>();

        for (OrderItem item : items) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("상품 없음"));
            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("재고 부족");
            }
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);

            item.setOrder(order);
            item.setPriceAtOrder(product.getPrice());
            totalPrice += product.getPrice() * item.getQuantity();
            savedItems.add(orderItemRepository.save(item));
        }

        order.setTotalPrice(totalPrice);
        order.setOrderItems(savedItems);
        return orderRepository.save(order);
    }
}
