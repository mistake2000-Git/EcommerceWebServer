package com.ecommerce.ecommerceapp.controller;

import com.ecommerce.ecommerceapp.common.ApiResponse;
import com.ecommerce.ecommerceapp.model.Category;
import com.ecommerce.ecommerceapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/createCategory")
    public ResponseEntity<ApiResponse> createCategory(@Valid Category category){
        if(Objects.nonNull(categoryService.readCategory(category.getCategoryName())))
        {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false,"Category already exist"), HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"created category"),HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories()
    {
        List<Category> body = categoryService.listCategory();
        return new ResponseEntity<>(body,HttpStatus.OK);
    }

    @PostMapping("/update/{categoryID}")
        public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Integer categoryID,@Valid @RequestBody Category category)
        {

            //Check if category exist
            if(Objects.nonNull(categoryService.readCategory(categoryID))){
                //If the category exist
                categoryService.updateCategory(categoryID,category);
                return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Update category sucessfully"),HttpStatus.OK);
            }
            //If the category does not exist
            return new ResponseEntity<ApiResponse>(new ApiResponse(false,"The category ID does not exist"),HttpStatus.NOT_FOUND);
        }
}


