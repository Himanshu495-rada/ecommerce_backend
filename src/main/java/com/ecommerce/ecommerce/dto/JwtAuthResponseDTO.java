package com.ecommerce.ecommerce.dto;

import lombok.Data;

@Data
public class JwtAuthResponseDTO {

    private String accessToken;
    private String tokenType = "Bearer";
    private String role;
    private Long userId;

}
