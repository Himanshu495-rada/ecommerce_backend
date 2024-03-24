package com.ecommerce.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.dto.UserDTO;
import com.ecommerce.ecommerce.services.SignupService;

@RestController
@RequestMapping("/api/user")
public class SignupController {
    
    @Autowired
    private SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody UserDTO userDTO) {
        UserDTO newUserDTO = signupService.signup(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUserDTO);
    }
}