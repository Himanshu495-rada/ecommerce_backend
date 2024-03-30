package com.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private Long addressId;
    private String addressDetail;
    private UserDTO user;

}
