package com.nisar.ecommerce.service;

import com.nisar.ecommerce.model.Product;
import com.nisar.ecommerce.payload.ProductDTO;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductDTO addProduct(Product product, Long categoryId);
}
