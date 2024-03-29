package com.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class CartItemDTO {

    private Long cartItemId;
    private int quantity;
    private CartDTO cart;
    private ProductDTO product;

}
