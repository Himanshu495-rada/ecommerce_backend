package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.dto.JwtAuthResponseDTO;
import com.ecommerce.ecommerce.dto.LoginDTO;
import com.ecommerce.ecommerce.dto.UserDTO;
import com.ecommerce.ecommerce.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

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