package com.ecommerce.ecommerceapp.model;

import org.hibernate.mapping.Join;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="orderitems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="quantity")
    private int quantity;
    @Column(name = "price")
    private double price;
    @Column(name= "created_date")
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name="order_id",referencedColumnName = "id")
    private Order order;
    @ManyToOne
    @JoinColumn(name="product_id",referencedColumnName = "id")
    private Product product;


    public OrderItem(int quantity, double price, Date createdDate, Order order, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.createdDate = createdDate;
        this.order = order;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public OrderItem(){}
}
