package com.ecommerce.ecommerceapp.repository;

import com.ecommerce.ecommerceapp.model.AuthenticationToken;
import com.ecommerce.ecommerceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findAuthenticationTokenByUser(User user);
    AuthenticationToken findAuthenticationTokenByToken(String token);
}
