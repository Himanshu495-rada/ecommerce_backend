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
    @GetMapping("/{userId}") //http://localhost:8083/api/cart/3
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId){
        CartDTO cartDTO = cartService.getCartByUser(userId);
        return ResponseEntity.ok(cartDTO);
    }

    //get all cart items
    @GetMapping("/{cartId}/items") //http://localhost:8083/api/cart/1/items
    public ResponseEntity<List<CartItemDTO>> getAllCartItems(@PathVariable Long cartId) {
        List<CartItemDTO> cartItemDTOs = cartService.getAllCartItems(cartId);
        return ResponseEntity.ok(cartItemDTOs);
    }

    //add product or item to cart
    @PostMapping("/{cartId}/add/{productId}") //http://localhost:8083/api/cart/1/add/3
    public ResponseEntity<Void> addItemToCart(@PathVariable Long cartId, @PathVariable Long productId){
        cartService.addItemToCart(cartId, productId);
        return ResponseEntity.ok().build();
    }

    //delete item from cart
    @DeleteMapping("/{cartId}/item/{cartItemId}")  //http://localhost:8083/api/cart/1/item/1
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        cartService.removeItemFromCart(cartId, cartItemId);
        return ResponseEntity.noContent().build();
    }

    //edit cart items quantity
    @PutMapping("/{cartId}/item/{cartItemId}") //http://localhost:8083/api/cart/1/item/1
    public ResponseEntity<Void> updateCartItem(@PathVariable Long cartId, @PathVariable Long cartItemId, @RequestParam int quantity) {
        cartService.updateCartItem(cartItemId, quantity);
        return ResponseEntity.ok().build();
    }

}
