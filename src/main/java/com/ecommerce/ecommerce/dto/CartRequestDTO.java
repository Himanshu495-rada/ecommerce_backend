package com.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class CartRequestDTO {

    private Long userId;
    private Long productId;
    private int quantity;

}
