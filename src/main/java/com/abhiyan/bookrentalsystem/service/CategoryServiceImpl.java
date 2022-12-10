package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.converter.CategoryDtoConverter;
import com.abhiyan.bookrentalsystem.dto.CategoryDto;
import com.abhiyan.bookrentalsystem.model.Category;
import com.abhiyan.bookrentalsystem.repository.CategoryRepo;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepo categoryRepo;

    private final CategoryDtoConverter categoryDtoConverter;

    public CategoryServiceImpl(CategoryRepo categoryRepo, CategoryDtoConverter categoryDtoConverter) {
        this.categoryRepo = categoryRepo;
        this.categoryDtoConverter = categoryDtoConverter;
    }

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        Category category = categoryDtoConverter.dtoToEntity(categoryDto);
        categoryRepo.save(category);
    }


}
