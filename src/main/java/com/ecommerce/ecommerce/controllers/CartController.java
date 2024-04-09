package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.dto.CartDTO;
import com.ecommerce.ecommerce.dto.CartItemDTO;
import com.ecommerce.ecommerce.dto.CartItemUpdateDTO;
import com.ecommerce.ecommerce.dto.CartRequestDTO;
import com.ecommerce.ecommerce.services.CartService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    //get user's cart
    @GetMapping("/{userId}") //http://localhost:8083/api/cart/3
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId){
        CartDTO cartDTO = cartService.getCartByUser(userId);
        return ResponseEntity.ok(cartDTO);
    }

    //get all cart items
    @GetMapping("/{userId}/items") //http://localhost:8083/api/cart/1/items
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<CartItemDTO>> getAllCartItems(@PathVariable Long userId) {
        List<CartItemDTO> cartItemDTOs = cartService.getAllCartItemsByUser(userId);
        return ResponseEntity.ok(cartItemDTOs);
    }

    //add product or item to cart
    @PostMapping("/add") //http://localhost:8083/api/cart/1/add/3
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Void> addItemToCart(@ModelAttribute CartRequestDTO cartRequestDTO){
        cartService.addItemToCart(cartRequestDTO);
        return ResponseEntity.ok().build();
    }

    //delete item from cart
    @DeleteMapping("/{cartItemId}")  //http://localhost:8083/api/cart/1/item/1
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartItemId) {
        cartService.removeItemFromCart(cartItemId);
        return ResponseEntity.noContent().build();
    }

    //edit cart items quantity
    @PutMapping("/update") //http://localhost:8083/api/cart/1/item/1
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Void> updateCartItemQuantity(@ModelAttribute CartItemUpdateDTO cartItemUpdateDTO) {
        cartService.updateCartItemQuantity(cartItemUpdateDTO);
        return ResponseEntity.ok().build();
    }

}
