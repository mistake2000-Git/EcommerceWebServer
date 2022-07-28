package com.ecommerce.ecommerceapp.exceptions;

public class ProductNotExistException extends Exception{
    public ProductNotExistException(String msg)
    {
        super(msg);
    }
}
