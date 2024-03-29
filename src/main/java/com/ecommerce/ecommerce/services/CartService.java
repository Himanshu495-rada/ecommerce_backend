package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dto.CartDTO;
import com.ecommerce.ecommerce.dto.CartItemDTO;
import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.entities.Cart;
import com.ecommerce.ecommerce.entities.CartItem;
import com.ecommerce.ecommerce.entities.Product;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repositories.CartItemRepository;
import com.ecommerce.ecommerce.repositories.CartRepository;
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
    private ModelMapper modelMapper;

    public CartDTO getCartByUser(Long userId) {
        User user = new User(); // Replace with logic to retrieve user
        user.setUserId(userId);
        Cart cart = cartRepository.findByUser(user);
        return modelMapper.map(cart, CartDTO.class);
    }

    public void addItemToCart(Long cartId, Long productId) {
        Product product = modelMapper.map(productService.getProduct(productId), Product.class);
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart with ID " + cartId + " not found"));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItemRepository.save(cartItem);

        cart.setTotalAmount(calculateCartTotalAmount(cart)); // Update total amount
        cartRepository.save(cart);
    }

    public void removeItemFromCart(Long cartId, Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);

        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart with ID " + cartId + " not found"));
        cart.setTotalAmount(calculateCartTotalAmount(cart));

        cartRepository.save(cart);
    }

    public void updateCartItem(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item with ID " + cartItemId + " not found"));
        cartItem.setQuantity(quantity);

        Long cartId = cartItem.getCart().getCartId();
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart with ID " + cartId + " not found"));
        cart.setTotalAmount(calculateCartTotalAmount(cart));

        cartItemRepository.save(cartItem);
    }

    public List<CartItemDTO> getAllCartItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart with ID " + cartId + " not found"));
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        return cartItems.stream().map(cartItem -> modelMapper.map(cartItem, CartItemDTO.class)).toList();
    }

    private Long calculateCartTotalAmount(Cart cart) {
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        return cartItems.stream().mapToLong(item -> (long) (item.getProduct().getProductPrice() * item.getQuantity())).sum();
    }

}
