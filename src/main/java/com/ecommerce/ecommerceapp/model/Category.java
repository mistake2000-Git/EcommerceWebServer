package com.ecommerce.ecommerceapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "categories")
public class Category {
    public Category(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="category_name")
    private @NotBlank String categoryName;
    private @NotBlank String description;
        @Column(name = "image_url")
    private @NotBlank String imageUrl;
    public Category(@NotBlank String categoryName,@NotBlank String description){
        this.categoryName = categoryName;
        this.description = description;
    }
    public Category(@NotBlank String categoryName,@NotBlank String description,String imageUrl)
    {
        this.categoryName = categoryName;
        this.description = description;
        this.imageUrl = imageUrl;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgageUrl() {
        return imageUrl;
    }

    public void setImgageUrl(String imgageUrl) {
        this.imageUrl = imgageUrl;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", imgageUrl='" + imageUrl + '\'' +
                '}';
    }

}