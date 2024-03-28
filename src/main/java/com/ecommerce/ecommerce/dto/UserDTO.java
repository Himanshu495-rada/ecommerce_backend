package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
public class UserDTO {

	private Long userId;
    private String username;
    
//    @JsonIgnore
    private String password; // Exclude password from serialization
    
    private String name;
    private RoleDTO role;

}

