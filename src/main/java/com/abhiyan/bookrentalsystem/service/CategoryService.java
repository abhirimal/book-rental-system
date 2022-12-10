package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.dto.CategoryDto;
import com.abhiyan.bookrentalsystem.model.Category;

import java.util.List;

public interface CategoryService {

    public void saveCategory(CategoryDto categoryDto);

    public List<Category> viewCategories();
}
