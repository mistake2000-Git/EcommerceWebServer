package com.ecommerce.ecommerceapp.service;

import com.ecommerce.ecommerceapp.dto.cart.AddCartDto;
import com.ecommerce.ecommerceapp.dto.cart.CartDto;
import com.ecommerce.ecommerceapp.dto.cart.CartItemDto;
import com.ecommerce.ecommerceapp.exceptions.CartItemDoesNotExistException;
import com.ecommerce.ecommerceapp.model.Cart;
import com.ecommerce.ecommerceapp.model.Product;
import com.ecommerce.ecommerceapp.model.User;
import com.ecommerce.ecommerceapp.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    public void addToCart(AddCartDto addCartDto, User user, Product product)
    {
        Cart cart = new Cart(product,user, addCartDto.getQuantity());
        cartRepository.save(cart);
    }

    public CartDto readCart(User user) throws CartItemDoesNotExistException
    {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        if(cartList.isEmpty())
            throw new CartItemDoesNotExistException("User doest not have item in cart");
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        double totalCost = 0;
        double price;
        int quantity;
        for (Cart cart:cartList)
        {
            price = cart.getProduct().getPrice();
            quantity = cart.getQuantity();
            cartItemDtos.add(new CartItemDto(cart));
            totalCost += price*quantity;
        }
        return new CartDto(cartItemDtos,totalCost);
    }
    public void deleteCartItem(int cartItemId,User user) throws CartItemDoesNotExistException{
        Optional<Cart>optionalCart = cartRepository.findById(cartItemId);

        if(!optionalCart.isPresent()){
            throw new CartItemDoesNotExistException("Item does not exist in cart of user");
        }
        if(optionalCart.get().getUser()!=user)
        {
            throw new CartItemDoesNotExistException("User is not valid");
        }
        cartRepository.deleteById(cartItemId);
    }
}
