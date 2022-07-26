package com.ecommerce.ecommerceapp.service;

import com.ecommerce.ecommerceapp.model.Category;
import com.ecommerce.ecommerceapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category readCategory(String categoryName)
    {
        return categoryRepository.findByCategoryName(categoryName);
    }
    public Optional<Category> readCategory(int categoryID)
    {
        return categoryRepository.findById(categoryID);
    }

    public List<Category> listCategory()
    {
        return categoryRepository.findAll();
    }
    public void updateCategory(int categoryID,Category newCategory)
    {
        Category category = categoryRepository.findById(categoryID).get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setImgageUrl(newCategory.getImgageUrl());
        categoryRepository.save(category);
    }
    public void createCategory(Category category){
        categoryRepository.save(category);
    }

}
