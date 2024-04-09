package com.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class CartItemUpdateDTO {

    private Long cartItemId;
    private int quantity;

}
