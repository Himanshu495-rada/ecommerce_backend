package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.entities.Cart;
import com.ecommerce.ecommerce.entities.CartItem;
import com.ecommerce.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartAndProduct(Cart cart, Product product);

    List<CartItem> findByCart(Cart cart);

}
