package com.ecommerce.ecommerceapp.service;

import com.ecommerce.ecommerceapp.model.User;
import com.ecommerce.ecommerceapp.model.WishList;
import com.ecommerce.ecommerceapp.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    @Autowired
    private WishListRepository wishListRepository;

    public void saveWishList(WishList wishList)
    {
        wishListRepository.save(wishList);
    }
    public List<WishList> readUserWishList(User user){
        return wishListRepository.findWishListByUserOrderByCreatedDateDesc(user);
    }
}
