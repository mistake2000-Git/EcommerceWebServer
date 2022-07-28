package com.ecommerce.ecommerceapp.controller;

import com.ecommerce.ecommerceapp.common.ApiResponse;
import com.ecommerce.ecommerceapp.dto.cart.AddCartDto;
import com.ecommerce.ecommerceapp.dto.cart.CartDto;
import com.ecommerce.ecommerceapp.dto.cart.CartItemDto;
import com.ecommerce.ecommerceapp.exceptions.AuthenticationFailException;
import com.ecommerce.ecommerceapp.exceptions.CartItemDoesNotExistException;
import com.ecommerce.ecommerceapp.exceptions.ProductNotExistException;
import com.ecommerce.ecommerceapp.model.Cart;
import com.ecommerce.ecommerceapp.model.Product;
import com.ecommerce.ecommerceapp.model.User;
import com.ecommerce.ecommerceapp.service.AuthenticationService;
import com.ecommerce.ecommerceapp.service.CartService;
import com.ecommerce.ecommerceapp.service.ProductService;
import io.swagger.annotations.Api;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @PostMapping("/addCart")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddCartDto addCartDto, @RequestParam("token") String token)
        throws AuthenticationFailException, ProductNotExistException {
        //authenticate
        authenticationService.authenticate(token);
        //get user sign in
        User user = authenticationService.getTokenUser(token);

        //find the product and add to cart
        Product product = productService.findProduct(addCartDto.getProductId());
        cartService.addToCart(addCartDto,user,product);
        return new ResponseEntity<>(new ApiResponse(true,"Added product to cart"), HttpStatus.CREATED);
    }

    @GetMapping("user/Cart")
    public ResponseEntity<CartDto> readUserCart(@RequestParam("token") String token)
            throws AuthenticationFailException,CartItemDoesNotExistException{
        authenticationService.authenticate(token);
        User user = authenticationService.getTokenUser(token);
        CartDto cartDto = cartService.readCart(user);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }

    @DeleteMapping("/deleteCart/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteUserCartItem(@PathVariable("cartItemId") int cartItemId,@RequestParam("token") String token)
        throws AuthenticationFailException, CartItemDoesNotExistException{
        authenticationService.authenticate(token);
        User user = authenticationService.getTokenUser(token);
        cartService.deleteCartItem(cartItemId,user);
        return new ResponseEntity<>(new ApiResponse(true,"delete cart item successfully"), HttpStatus.OK);
    }
}
