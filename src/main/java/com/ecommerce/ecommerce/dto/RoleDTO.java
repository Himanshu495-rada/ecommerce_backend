package com.ecommerce.ecommerce.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class RoleDTO {
    private Long id;
    private String roleName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public RoleDTO(Long id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}
	public RoleDTO() {
		super();
	}
    
    

    // Constructors, getters, setters
}