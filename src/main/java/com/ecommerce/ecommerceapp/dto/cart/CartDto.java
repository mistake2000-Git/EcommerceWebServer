package com.ecommerce.ecommerceapp.dto.cart;

import java.util.List;

public class CartDto {
    private int id;
    private List<CartItemDto> cartItemDtoList;

    private double totalCost;

    public CartDto(List<CartItemDto> cartItemDtos, double totalCost)
    {
        this.cartItemDtoList = cartItemDtos;
        this.totalCost = totalCost;
    }

    public List<CartItemDto> getCartItemDtoList() {
        return cartItemDtoList;
    }

    public void setCartItemDtoList(List<CartItemDto> cartItemDtoList) {
        this.cartItemDtoList = cartItemDtoList;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
