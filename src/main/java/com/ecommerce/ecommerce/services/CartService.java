package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dto.*;
import com.ecommerce.ecommerce.entities.Cart;
import com.ecommerce.ecommerce.entities.CartItem;
import com.ecommerce.ecommerce.entities.Product;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repositories.CartItemRepository;
import com.ecommerce.ecommerce.repositories.CartRepository;
import com.ecommerce.ecommerce.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CartDTO getCartByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found"));
        Cart cart = cartRepository.findByUser(user);
        return modelMapper.map(cart, CartDTO.class);
    }

    public void addItemToCart(CartRequestDTO cartRequestDTO) {
        Product product = modelMapper.map(productService.getProduct(cartRequestDTO.getProductId()), Product.class);
        Cart cart = modelMapper.map(getCartByUser(cartRequestDTO.getUserId()), Cart.class);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(cartRequestDTO.getQuantity());
        cartItemRepository.save(cartItem);

        cart.setTotalAmount(calculateCartTotalAmount(cart)); // Update total amount
        cartRepository.save(cart);
    }

    public void removeItemFromCart(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new IllegalArgumentException("Cartitem with id: " + cartItemId + " is not found"));
        cartItemRepository.deleteById(cartItemId);

        Cart cart = cartRepository.findById(cartItem.getCart().getCartId()).orElseThrow(() -> new IllegalArgumentException("Cart with ID " + cartItem.getCart().getCartId() + " not found"));
        cart.setTotalAmount(calculateCartTotalAmount(cart));

        cartRepository.save(cart);
    }

    public void updateCartItemQuantity(CartItemUpdateDTO cartItemUpdateDTO) {
        CartItem cartItem = cartItemRepository.findById(cartItemUpdateDTO.getCartItemId())
                .orElseThrow(() -> new IllegalArgumentException("Cart item with ID " + cartItemUpdateDTO.getCartItemId() + " not found"));
        cartItem.setQuantity(cartItemUpdateDTO.getQuantity());

        Long cartId = cartItem.getCart().getCartId();
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart with ID " + cartId + " not found"));
        cart.setTotalAmount(calculateCartTotalAmount(cart));

        cartItemRepository.save(cartItem);
    }

    public List<CartItemDTO> getAllCartItemsByUser(Long userId) {
        Cart cart = modelMapper.map(getCartByUser(userId), Cart.class);

        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        return cartItems.stream().map(cartItem -> modelMapper.map(cartItem, CartItemDTO.class)).toList();
    }

    private Long calculateCartTotalAmount(Cart cart) {
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        return cartItems.stream().mapToLong(item -> (long) (item.getProduct().getSellingPrice() * item.getQuantity())).sum();
    }

}
