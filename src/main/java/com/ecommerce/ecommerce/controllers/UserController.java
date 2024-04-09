package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.dto.AddressDTO;
import com.ecommerce.ecommerce.dto.JwtAuthResponseDTO;
import com.ecommerce.ecommerce.dto.LoginDTO;
import com.ecommerce.ecommerce.dto.UserDTO;
import com.ecommerce.ecommerce.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.services.UserService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable Long userId) {
        UserDTO user = userService.getUserInfo(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO userDTO) {
        String message = userService.signup(userDTO);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        JwtAuthResponseDTO jwtAuthResponseDTO = userService.login(loginDTO);

        return ResponseEntity.ok(jwtAuthResponseDTO);
    }
}