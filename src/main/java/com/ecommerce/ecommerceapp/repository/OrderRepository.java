package com.ecommerce.ecommerceapp.repository;

import com.ecommerce.ecommerceapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
