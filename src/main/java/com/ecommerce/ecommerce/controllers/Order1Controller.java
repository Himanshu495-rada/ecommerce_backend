package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.dto.CartDTO;
import com.ecommerce.ecommerce.dto.Order1DTO;
import com.ecommerce.ecommerce.entities.Order1;
import com.ecommerce.ecommerce.entities.OrderItem;
import com.ecommerce.ecommerce.services.Order1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class Order1Controller {

    @Autowired
    private Order1Service order1Service;

    @GetMapping
    @PreAuthorize("hashRole('CUSTOMER')")
    private ResponseEntity<List<Order1DTO>> getUserOrders(@RequestParam Long userId){
        return ResponseEntity.ok(order1Service.getUserOrders(userId));
    }

    @PostMapping("/{addressId}")
    @PreAuthorize("hashRole('CUSTOMER')")
    public ResponseEntity<Order1DTO> createOrder(@RequestBody CartDTO cartDTO, @PathVariable Long addressId){
        Order1DTO createdOrderDTO = order1Service.createOrder(cartDTO.getCartId(), cartDTO.getUser().getUserId(), addressId);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDTO);
    }

}
