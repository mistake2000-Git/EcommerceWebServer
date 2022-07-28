package com.ecommerce.ecommerceapp.dto.cart;

import com.ecommerce.ecommerceapp.model.Cart;
import com.ecommerce.ecommerceapp.model.Product;

public class CartItemDto {
    private int id;
    private int quantity;
    private Product product;

    public CartItemDto(Cart cart) {
        this.quantity = cart.getQuantity();
        this.id= cart.getId();
        this.product = cart.getProduct();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
