package com.myshop.dto;

import lombok.Data;

import java.util.List;

public class OrderDto {

    @Data
    public static class OrderRequest {
        private Long userId;
        private List<OrderItemDto> items;
    }

    @Data
    public static class OrderItemDto {
        private Long productId;
        private int quantity;

        public com.myshop.entity.Product toProduct() {
            com.myshop.entity.Product p = new com.myshop.entity.Product();
            p.setId(productId);
            return p;
        }
    }
}