package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dto.AddressDTO;
import com.ecommerce.ecommerce.dto.Order1DTO;
import com.ecommerce.ecommerce.dto.OrderRequestDTO;
import com.ecommerce.ecommerce.entities.*;
import com.ecommerce.ecommerce.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Order1Service {

    @Autowired
    private Order1Repository order1Repository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Order1DTO> getUserOrders(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + "not found"));
        List<Order1> orders = order1Repository.findByUser(user);
        return orders.stream().map(order -> modelMapper.map(order, Order1DTO.class)).toList();
    }

    public Order1DTO createOrder(OrderRequestDTO orderRequestDTO) {
        User user = userRepository.findById(orderRequestDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("User with ID " + orderRequestDTO.getUserId() + "not found"));
        Cart cart = cartRepository.findByUser(user);
        Address address = addressRepository.findById(orderRequestDTO.getAddressId()).orElseThrow(() -> new IllegalArgumentException("Address with ID " + orderRequestDTO.getAddressId() + "not found"));

        Order1 order1 = new Order1();
        order1.setUser(user);
        order1.setAddress(address);
        order1.setOrderPrice(cart.getTotalAmount());

        List<OrderItem> orderItems = new ArrayList<>();
        List<CartItem> cartItems = cartItemRepository.findByCart(cart).stream().map(cartItem -> modelMapper.map(cartItem, CartItem.class)).toList();
        for(CartItem cartItem: cartItems){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order1);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);
        }

        order1Repository.save(order1);
        orderItemRepository.saveAll(orderItems);

        cartItemRepository.deleteAll(cartItems);
        cart.setTotalAmount(Long.valueOf(0));
        cartRepository.save(cart);

        return modelMapper.map(order1, Order1DTO.class);
    }

}
