package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class UserDTO {

	private Long id;
    private String username;
    
//    @JsonIgnore
    private String password; // Exclude password from serialization
    
    private String name;
    private RoleDTO role;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RoleDTO getRole() {
		return role;
	}
	public void setRole(RoleDTO role) {
		this.role = role;
	}
	public UserDTO(Long id, String username, String password, String name, RoleDTO role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = role;
	}
	public UserDTO() {
		super();
	}
    
    

    // Constructors, getters, setters
}

