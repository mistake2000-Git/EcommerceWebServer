package com.ecommerce.ecommerceapp.service;

import com.ecommerce.ecommerceapp.dto.product.ProductDto;
import com.ecommerce.ecommerceapp.exceptions.ProductNotExistException;
import com.ecommerce.ecommerceapp.model.Category;
import com.ecommerce.ecommerceapp.model.Product;
import com.ecommerce.ecommerceapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public void addProduct(ProductDto newProduct, Category category)
    {
        Product product = getProductFromDto(newProduct,category);
        productRepository.save(product);

    }
    public static Product getProductFromDto(ProductDto productDto,Category category)
    {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        return product;
    }
    public List<ProductDto> listProduct()
    {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product:products)
        {
            productDtos.add(new ProductDto(product));
        }
        return productDtos;
    }
    public void updateProduct(ProductDto productDto,Category category,Integer productId){
        Product product = getProductFromDto(productDto, category);
        product.setId(productId);
        productRepository.save(product);
    }
    public Product findProduct(int productId)throws ProductNotExistException {
        Product product = productRepository.findProductById(productId);
        if(!Objects.nonNull(product)){
            throw new ProductNotExistException("Product does not exist");
        }
       return product;
    }

}
