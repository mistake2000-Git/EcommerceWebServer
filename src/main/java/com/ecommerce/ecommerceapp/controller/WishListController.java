package com.ecommerce.ecommerceapp.controller;

import com.ecommerce.ecommerceapp.common.ApiResponse;
import com.ecommerce.ecommerceapp.dto.product.ProductDto;
import com.ecommerce.ecommerceapp.exceptions.AuthenticationFailException;
import com.ecommerce.ecommerceapp.exceptions.ProductNotExistException;
import com.ecommerce.ecommerceapp.model.Product;
import com.ecommerce.ecommerceapp.model.User;
import com.ecommerce.ecommerceapp.model.WishList;
import com.ecommerce.ecommerceapp.service.AuthenticationService;
import com.ecommerce.ecommerceapp.service.ProductService;
import com.ecommerce.ecommerceapp.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wishList")
public class WishListController {
    @Autowired
    WishListService wishListService;
    @Autowired
    ProductService productService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addWishList(@RequestBody ProductDto productDto, @RequestParam("token") String token)
            throws AuthenticationFailException, ProductNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getTokenUser(token);
        Product product = productService.findProduct(productDto.getId());
        WishList wishList = new WishList(user,product);
        wishListService.saveWishList(wishList);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Add product to wish list successfully"), HttpStatus.CREATED);

    }
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishListProduct(@PathVariable("token") String token) throws AuthenticationFailException
    {
        authenticationService.authenticate(token);
        User user = authenticationService.getTokenUser(token);
        List <WishList> wishLists = wishListService.readUserWishList(user);
        List <ProductDto> products = new ArrayList<>();
        for(WishList wishList:wishLists)
        {
            products.add(new ProductDto(wishList.getProduct()));
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

}
