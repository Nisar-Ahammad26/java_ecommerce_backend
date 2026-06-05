package com.nisar.ecommerce.repository;

import com.nisar.ecommerce.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category,Long> {


    Category findByCategoryName(@NotBlank @Size(min = 5, message = "Size must me greater than 5 letters") String categoryName);
}
