package com.ecommerce.ecommerceapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControlerAdvice {
    @ExceptionHandler(value = CustomException.class)
    public final ResponseEntity<String> handleUpdateFailException(CustomException exception)
    {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ProductNotExistException.class)
    public final ResponseEntity<String> handleUpdateFailException(ProductNotExistException exception)
    {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CartItemDoesNotExistException.class)
    public final ResponseEntity<String> handdleCartItemDoesNotExistEception(CartItemDoesNotExistException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
