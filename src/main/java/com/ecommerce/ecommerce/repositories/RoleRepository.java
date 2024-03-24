package com.ecommerce.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.ecommerce.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // You can define additional methods here if needed
}

