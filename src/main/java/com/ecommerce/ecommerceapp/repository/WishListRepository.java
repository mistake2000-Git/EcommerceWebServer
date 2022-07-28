package com.ecommerce.ecommerceapp.repository;

import com.ecommerce.ecommerceapp.model.User;
import com.ecommerce.ecommerceapp.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Integer> {
        List<WishList> findWishListByUserOrderByCreatedDateDesc(User user);
}
