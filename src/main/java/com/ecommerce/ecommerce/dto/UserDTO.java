package com.ecommerce.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDTO {

    private Long userId;
    private String username;

    @JsonIgnore
    private String password;
    private String name;
    private String email;
    private RoleDTO role;

}
