package com.nisar.ecommerce.controller;

import com.nisar.ecommerce.config.AppConstant;
import com.nisar.ecommerce.model.Category;
import com.nisar.ecommerce.payload.CategoryDTO;
import com.nisar.ecommerce.payload.CategoryResponse;
import com.nisar.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {



    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("api/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO savedCategory=categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory,HttpStatus.CREATED);
    }


    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
                                                             @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSize,
                                                             @RequestParam(name="sortBy",defaultValue = AppConstant.SORT_CATAGORIES_BY,required = false) String sortBy,
                                                             @RequestParam(name="sortOrder",defaultValue = AppConstant.SORT_DIR,required = false) String sortOrder) {

           CategoryResponse categoryResponse = categoryService.getAllCategory(pageNumber,pageSize,sortBy,sortOrder);
            return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);

    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable Long categoryId)
    {
           CategoryDTO savedCategory =categoryService.updateCategory(categoryDTO,categoryId);
            return new ResponseEntity<>(savedCategory,HttpStatus.OK);
        }


    @DeleteMapping("api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId)
    {
        CategoryDTO savedCategory = categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(savedCategory);
    }
    }



