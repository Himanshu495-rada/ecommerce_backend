package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.dto.CartDTO;
import com.ecommerce.ecommerce.dto.Order1DTO;
import com.ecommerce.ecommerce.dto.OrderRequestDTO;
import com.ecommerce.ecommerce.entities.Order1;
import com.ecommerce.ecommerce.entities.OrderItem;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repositories.Order1Repository;
import com.ecommerce.ecommerce.repositories.UserRepository;
import com.ecommerce.ecommerce.services.Order1Service;
import com.ecommerce.ecommerce.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/order")
public class Order1Controller {

    @Autowired
    private Order1Service order1Service;

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<Order1DTO>> getOrders(@PathVariable Long userId){
        List<Order1DTO> orders = order1Service.getUserOrders(userId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Order1DTO> createOrder(@ModelAttribute OrderRequestDTO orderRequestDTO){
        Order1DTO createdOrderDTO = order1Service.createOrder(orderRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDTO);
    }

}
