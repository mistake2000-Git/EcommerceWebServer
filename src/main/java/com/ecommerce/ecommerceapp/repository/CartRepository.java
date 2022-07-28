package com.ecommerce.ecommerceapp.repository;

import com.ecommerce.ecommerceapp.dto.cart.CartDto;
import com.ecommerce.ecommerceapp.model.Cart;
import com.ecommerce.ecommerceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
