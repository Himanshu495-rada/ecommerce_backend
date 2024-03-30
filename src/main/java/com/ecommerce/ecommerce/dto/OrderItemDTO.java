package com.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class OrderItemDTO {

    private Long orderItemId;
    private int quantity;
    private Order1DTO order1DTO;
    private ProductDTO productDTO;

}