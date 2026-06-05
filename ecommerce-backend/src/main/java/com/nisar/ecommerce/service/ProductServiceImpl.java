package com.nisar.ecommerce.service;

import com.nisar.ecommerce.exception.ResourceNotFoundException;
import com.nisar.ecommerce.model.Category;
import com.nisar.ecommerce.model.Product;
import com.nisar.ecommerce.payload.ProductDTO;
import com.nisar.ecommerce.repository.CategoryRepository;
import com.nisar.ecommerce.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(Product product, Long categoryId) {
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        product.setCategory(category);
        double specialPrice=product.getPrice()-((product.getDiscount()*0.01)*product.getPrice());
        product.setSpecialPrice(specialPrice);
        product.setImage("default.png");
        product.setDescription(product.getDescription());
        Product savedProduct=productRepository.save(product);
        return modelMapper.map(savedProduct,ProductDTO.class);
    }
}
