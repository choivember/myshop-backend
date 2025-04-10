package com.myshop.dto;

import com.myshop.entity.Product;
import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private int price;
    private int stock;
    private String imageUrl;

    // DTO → Entity 변환
    public Product toEntity() {
        Product p = new Product();
        p.setName(this.name);
        p.setDescription(this.description);
        p.setPrice(this.price);
        p.setStock(this.stock);
        p.setImageUrl(this.imageUrl);
        return p;
    }
}