package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dto.CartDTO;
import com.ecommerce.ecommerce.dto.RoleDTO;
import com.ecommerce.ecommerce.dto.UserDTO;
import com.ecommerce.ecommerce.entities.Cart;
import com.ecommerce.ecommerce.entities.Role;
import com.ecommerce.ecommerce.repositories.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repositories.RoleRepository;
import com.ecommerce.ecommerce.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;


    public UserDTO signup(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setRole(roleRepository.findByRoleName(userDTO.getRole().getRoleName()));
        User savedUser = userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(savedUser);
        cart.setTotalAmount(Long.valueOf(0));
        cartRepository.save(cart);

        return modelMapper.map(savedUser, UserDTO.class);
    }
}
