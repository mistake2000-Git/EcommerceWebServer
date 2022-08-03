package com.ecommerce.ecommerceapp.controller;


import com.ecommerce.ecommerceapp.common.ApiResponse;
import com.ecommerce.ecommerceapp.exceptions.AuthenticationFailException;
import com.ecommerce.ecommerceapp.exceptions.CartItemDoesNotExistException;
import com.ecommerce.ecommerceapp.model.Order;
import com.ecommerce.ecommerceapp.model.OrderItem;
import com.ecommerce.ecommerceapp.model.User;
import com.ecommerce.ecommerceapp.service.AuthenticationService;
import com.ecommerce.ecommerceapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    OrderService orderService;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam("token") String token,@RequestParam("sessionId") String sessionId)
            throws AuthenticationFailException, CartItemDoesNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getTokenUser(token);
        orderService.placeOrder(user,sessionId);
        return new ResponseEntity<>(new ApiResponse(true,"Order has been placed"), HttpStatus.CREATED);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> listOrderOfUser(@RequestParam("token") String token) throws AuthenticationFailException{
        authenticationService.authenticate(token);
        User user = authenticationService.getTokenUser(token);
        List<Order>  orders = orderService.listUserOrders(user);
        return  new ResponseEntity<>(orders,HttpStatus.OK);
    }

}
