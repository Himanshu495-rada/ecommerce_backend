package com.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class Order1DTO {

    private Long orderId;
    private double orderPrice;
    private UserDTO user;
    private AddressDTO address;

}
