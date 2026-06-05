package com.nisar.ecommerce.service;

import com.nisar.ecommerce.model.Category;
import com.nisar.ecommerce.payload.CategoryDTO;
import com.nisar.ecommerce.payload.CategoryResponse;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface CategoryService {


    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryResponse getAllCategory(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
    CategoryDTO deleteCategory(Long categoryId);


}