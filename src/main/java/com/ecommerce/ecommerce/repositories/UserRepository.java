package com.ecommerce.ecommerce.repositories;
import com.ecommerce.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//UserRepository.java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 // You can define additional methods here if needed
}
