package com.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class CartDTO {

    private Long cartId;
    private Long totalAmount;
    private UserDTO user;

}
