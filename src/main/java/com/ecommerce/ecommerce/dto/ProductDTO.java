package com.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private double sellingPrice;
    private int remainingQuantity;
    private String image;

    private CategoryDTO category;
    private UserDTO user;
}
