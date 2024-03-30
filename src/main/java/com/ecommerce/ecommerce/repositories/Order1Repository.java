package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.entities.Order1;
import com.ecommerce.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order1Repository extends JpaRepository<Order1, Long> {

    List<Order1> findByUser(User user);

}
