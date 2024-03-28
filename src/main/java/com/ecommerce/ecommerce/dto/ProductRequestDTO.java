package com.ecommerce.ecommerce.dto;



import lombok.*;

@Data
public class ProductRequestDTO {
    private Long productId;
    private String productName;
    private String productDescription;
    private double productPrice;

    private int remainingQuantity;
    private Long categoryId;
    private Long userId;
}

