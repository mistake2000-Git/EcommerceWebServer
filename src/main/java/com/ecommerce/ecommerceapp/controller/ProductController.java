package com.ecommerce.ecommerceapp.controller;

import com.ecommerce.ecommerceapp.common.ApiResponse;
import com.ecommerce.ecommerceapp.dto.product.ProductDto;
import com.ecommerce.ecommerceapp.model.Category;
import com.ecommerce.ecommerceapp.service.CategoryService;
import com.ecommerce.ecommerceapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;


    // Add product API
    @PostMapping("/addProduct")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto)
    {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"category is invalid"),HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.addProduct(productDto,category);
        return new ResponseEntity<>(new ApiResponse(true,"Add product successfully"),HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getAllProduct()
    {
       List<ProductDto> productDtos = productService.listProduct();
       return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productID, @Valid @RequestBody ProductDto productDto)
    {
        Optional <Category> optionalCategory= categoryService.readCategory(productDto.getCategoryId());
        if(!optionalCategory.isPresent())
        {
            return new ResponseEntity<>(new ApiResponse(false,"invalid category"),HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.updateProduct(productDto,category,productID);
        return new ResponseEntity<>(new ApiResponse(true,"Update product successfully"),HttpStatus.OK);
    }
}
