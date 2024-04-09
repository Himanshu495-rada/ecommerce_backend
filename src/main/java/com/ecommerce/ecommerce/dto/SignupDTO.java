package com.ecommerce.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class SignupDTO {

    private Long userId;
    private String username;
    private String password;
    private String name;
    private String email;
    private RoleDTO role;

}
