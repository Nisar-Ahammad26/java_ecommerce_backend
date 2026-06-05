package com.nisar.ecommerce.service;

import com.nisar.ecommerce.payload.CategoryDTO;
import com.nisar.ecommerce.payload.CategoryResponse;
import com.nisar.ecommerce.repository.CategoryRepository;
import com.nisar.ecommerce.exception.APIException;
import com.nisar.ecommerce.exception.ResourceNotFoundException;
import com.nisar.ecommerce.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImple implements CategoryService{

   private CategoryRepository categoryRepository;

   public CategoryServiceImple(CategoryRepository categoryRepository)
   {
       this.categoryRepository=categoryRepository;
   }

   @Autowired
   private ModelMapper modelMapper;


    @Override
    public CategoryResponse getAllCategory(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder) {
       Sort sortByAndOrder=sortOrder.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails= PageRequest.of(pageNumber,pageSize,sortByAndOrder
        );
        Page<Category> categoryPage=categoryRepository.findAll(pageDetails);
        List<Category> categories = categoryPage.getContent();

        if (categories.isEmpty()) {
            throw new APIException("No category exist till now");
        }
        List<CategoryDTO> categoryDtos= categories.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDtos);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());
        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category=modelMapper.map(categoryDTO,Category.class);
       Category categoryFromDb=categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFromDb != null)
        {
            throw new APIException("Category with the same name :"+category.getCategoryName()+"already exists");
        }

         Category savedCategory= categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
      Category savedCategory=categoryRepository.findById(categoryId)
              .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
       categoryRepository.delete(savedCategory);
       return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory( CategoryDTO categoryDTO, Long categoryId) {

        Category savedCategory= categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
             Category category=modelMapper.map(categoryDTO,Category.class);
             category.setCategoryId(categoryId);
             savedCategory=categoryRepository.save(category);
//             savedCategory.setCategoryName(category.getCategoryName());
//             savedCategory=categoryRepository.save(savedCategory);

        return modelMapper.map(savedCategory,CategoryDTO.class);
    }


}
