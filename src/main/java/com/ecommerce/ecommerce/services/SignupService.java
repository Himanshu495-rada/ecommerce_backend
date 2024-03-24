package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.RoleDTO;
import com.ecommerce.ecommerce.dto.UserDTO;
import com.ecommerce.ecommerce.entities.Role;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repositories.RoleRepository;
import com.ecommerce.ecommerce.repositories.UserRepository;

@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository; // Assuming you have a RoleRepository
    
    public RoleDTO roleToRoleDTO(Role role) {
   	 RoleDTO role1=new RoleDTO();
   	 role1.setId(role.getId());
   	 role1.setRoleName(role.getRoleName());
   	 return role1;
   	
   	
   	
   }

    public UserDTO signup(UserDTO userDTO) {
        // Create a new User entity from the DTO
    	
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());
        newUser.setName(userDTO.getName());

        // Fetch the existing role from the database based on role ID
        Role role = roleRepository.findById(userDTO.getRole().getId())
                                  .orElseThrow(() -> new RuntimeException("Role not found"));

        // Associate the existing role with the user
        newUser.setRole(role);
        
        userDTO.setRole(roleToRoleDTO(role));
        
        // Save the new user to the database
        User savedUser = userRepository.save(newUser);
//        userDTO.setId(savedUser.getId());

        // Convert the saved user entity back to a DTO

        return new UserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getName(), userDTO.getRole());

    }
}
