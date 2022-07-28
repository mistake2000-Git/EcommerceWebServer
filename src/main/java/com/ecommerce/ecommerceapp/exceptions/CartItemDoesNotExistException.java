package com.ecommerce.ecommerceapp.exceptions;

public class CartItemDoesNotExistException extends Exception{
    public CartItemDoesNotExistException(String msg)
    {
        super(msg);
    }
}
