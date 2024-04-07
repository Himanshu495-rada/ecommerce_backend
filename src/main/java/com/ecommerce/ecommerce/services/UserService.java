package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dto.*;
import com.ecommerce.ecommerce.entities.Cart;
import com.ecommerce.ecommerce.entities.Role;
import com.ecommerce.ecommerce.exception.AuthAPIException;
import com.ecommerce.ecommerce.repositories.CartRepository;
import com.ecommerce.ecommerce.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String signup(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);

        Boolean stat = userRepository.existsByEmail(user.getEmail());
        if(userRepository.existsByEmail(user.getEmail())){
            throw new AuthAPIException(HttpStatus.BAD_REQUEST, "email already registered");
        }

        if(userRepository.existsByUsername(user.getUsername())){
            throw new AuthAPIException(HttpStatus.BAD_REQUEST, "Username already used");
        }

        user.setRole(roleRepository.findByRoleName(userDTO.getRole().getRoleName()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);



        if(user.getRole().getRoleName() == "customer"){
            Cart cart = new Cart();
            cart.setUser(savedUser);
            cart.setTotalAmount(Long.valueOf(0));
            cartRepository.save(cart);
        }

        return "User registered successfully";
    }

    public JwtAuthResponseDTO login(LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(),
                loginDTO.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        User user = userRepository.findByUsernameOrEmail(loginDTO.getUsernameOrEmail(), loginDTO.getUsernameOrEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

        JwtAuthResponseDTO jwtAuthResponseDTO = new JwtAuthResponseDTO();
        jwtAuthResponseDTO.setUserId(user.getUserId());
        jwtAuthResponseDTO.setRole(user.getRole().getRoleName());
        jwtAuthResponseDTO.setAccessToken(token);

        return jwtAuthResponseDTO;
    }
}
