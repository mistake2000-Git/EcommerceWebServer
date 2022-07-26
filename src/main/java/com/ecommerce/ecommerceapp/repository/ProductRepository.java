package com.ecommerce.ecommerceapp.repository;

import com.ecommerce.ecommerceapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
        Product findProductById(int productId);
}
