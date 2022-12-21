package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.dto.CategoryDto;
import com.abhiyan.bookrentalsystem.dto.ResponseDto;
import com.abhiyan.bookrentalsystem.model.Category;

import java.util.List;

public interface CategoryService {

    public ResponseDto saveCategory(CategoryDto categoryDto);

    public List<Category> viewCategories();

    public void deleteCategory(Integer id);

    public CategoryDto editCategory(Integer id);

    public CategoryDto updateCategory(Integer id, CategoryDto categoryDto);
}
