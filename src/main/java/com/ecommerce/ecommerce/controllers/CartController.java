package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.dto.CartDTO;
import com.ecommerce.ecommerce.dto.CartItemDTO;
import com.ecommerce.ecommerce.services.CartService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    //get user's cart
    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId){
        CartDTO cartDTO = cartService.getCartByUser(userId);
        return ResponseEntity.ok(cartDTO);
    }

    //add product or item to cart
    @PostMapping("/{cartId}/add/{productId}")
    public ResponseEntity<Void> addItemToCart(@PathVariable Long cartId, @PathVariable Long productId){
        cartService.addItemToCart(cartId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartId}/item/{cartItemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        cartService.removeItemFromCart(cartId, cartItemId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{cartId}/item/{cartItemId}")
    public ResponseEntity<Void> updateCartItem(@PathVariable Long cartId, @PathVariable Long cartItemId, @RequestParam int quantity) {
        cartService.updateCartItem(cartItemId, quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItemDTO>> getAllCartItems(@PathVariable Long cartId) {
        List<CartItemDTO> cartItemDTOs = cartService.getAllCartItems(cartId);
        return ResponseEntity.ok(cartItemDTOs);
    }

}
