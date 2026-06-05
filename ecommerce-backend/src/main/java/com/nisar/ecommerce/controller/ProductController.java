package com.nisar.ecommerce.controller;


import com.nisar.ecommerce.model.Product;
import com.nisar.ecommerce.payload.ProductDTO;
import com.nisar.ecommerce.repository.CategoryRepository;
import com.nisar.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;



    @GetMapping("/api/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProducts(@RequestBody Product product, @PathVariable Long categoryId)
    {
      ProductDTO producrDTO=productService.addProduct(product,categoryId);
      return new ResponseEntity<>(producrDTO, HttpStatus.CREATED);
    }
}
