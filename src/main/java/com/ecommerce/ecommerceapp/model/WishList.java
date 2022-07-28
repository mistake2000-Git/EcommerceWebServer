package com.ecommerce.ecommerceapp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="wishlist")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name="user_id")
    private User user;

    @Column(name="created_date")
    private Date createdDate;
    @ManyToOne()
    @JoinColumn(name="product_id")
    private Product product;

    public WishList(User user, Product product) {
        this.user = user;
        this.createdDate = new Date();
        this.product = product;
    }
    public WishList(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
