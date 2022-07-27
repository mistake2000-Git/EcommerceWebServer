package com.ecommerce.ecommerceapp.controller;

import com.ecommerce.ecommerceapp.dto.User.SignInDto;
import com.ecommerce.ecommerceapp.dto.User.SignInReponseDto;
import com.ecommerce.ecommerceapp.dto.User.SignUpDto;
import com.ecommerce.ecommerceapp.dto.User.SignUpResponseDto;
import com.ecommerce.ecommerceapp.exceptions.AuthenticationFailException;
import com.ecommerce.ecommerceapp.exceptions.CustomException;
import com.ecommerce.ecommerceapp.model.User;
import com.ecommerce.ecommerceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/signUp")
    public SignUpResponseDto signUp(@RequestBody  SignUpDto signUpDto) throws CustomException {
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signIn")
    public SignInReponseDto signIn(@RequestBody SignInDto signInDto) throws AuthenticationFailException,CustomException{
        return  userService.signIn(signInDto);
    }
}
