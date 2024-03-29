package com.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int remainingQuantity;

    private CategoryDTO category;
    private UserDTO user;
}
