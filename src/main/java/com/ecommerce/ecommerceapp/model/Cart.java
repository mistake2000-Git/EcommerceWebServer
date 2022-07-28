package com.ecommerce.ecommerceapp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="created_date")
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @ManyToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name= "user_id")
    private User user;

    private int quantity;
    public Cart(){

    }

    public Cart(Product product, User user, int quantity) {
        this.createdDate = new Date();
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
