package com.ecommerce.ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class UserResponseDTO {
    private Long id;
    private String username;


    private String name;
    private RoleDTO roleDTO;
}
