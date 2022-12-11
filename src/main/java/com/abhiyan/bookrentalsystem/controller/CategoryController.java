package com.abhiyan.bookrentalsystem.controller;

import com.abhiyan.bookrentalsystem.converter.CategoryDtoConverter;
import com.abhiyan.bookrentalsystem.dto.CategoryDto;
import com.abhiyan.bookrentalsystem.model.Category;
import com.abhiyan.bookrentalsystem.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryDtoConverter categoryDtoConverter;

    public CategoryController(CategoryService categoryService, CategoryDtoConverter categoryDtoConverter) {
        this.categoryService = categoryService;
        this.categoryDtoConverter = categoryDtoConverter;
    }

    @GetMapping("/add-category")
    public String addCategory(Model model) {
        CategoryDto categoryDto = new CategoryDto();
        model.addAttribute("categoryDto", categoryDto);
        return "category/addCategory";
    }

    @PostMapping("/add-category/new")
    public String addNewCategory(@Valid @ModelAttribute("categoryDto") CategoryDto categoryDto,
                                 BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("categoryDto",categoryDto);
            return "category/addCategory";
        }
        categoryService.saveCategory(categoryDto);
        return "redirect:/view-categories";
    }

    @GetMapping("/view-categories")
    public String viewAllCategories(Model model){
        List<Category> categories = categoryService.viewCategories();
        List<CategoryDto> categoryDto = categoryDtoConverter.entityToDto(categories);
        model.addAttribute("category",categoryDto);
        return "category/viewCategory";
    }

    @GetMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return "redirect:/view-categories";
    }

    @GetMapping("/edit-category/{id}")
    public String editCategory(@PathVariable Integer id, Model model){
        CategoryDto categoryDto = categoryService.editCategory(id);
        model.addAttribute("category",categoryDto);
        return "category/updateCategory";
    }

    @PostMapping("/update-category/{id}")
    public String updateCategory(@PathVariable Integer id, @Valid @ModelAttribute("category") CategoryDto categoryDto,
                                 BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("category", categoryDto);
            return "category/updateCategory";
        }
        categoryService.updateCategory(id,categoryDto);
        return "redirect:/view-categories";

    }


}