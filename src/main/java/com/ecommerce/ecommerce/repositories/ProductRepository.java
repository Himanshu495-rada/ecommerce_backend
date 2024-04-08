package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.entities.Product;
import com.ecommerce.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//   Product findByName(String name);

//    Optional<Product> findByName(String name);

//    List<Product> findByCategory(String category);
    Product findByImage(String image);

    List<Product> findByUser(User user);
}

