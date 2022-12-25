package com.abhiyan.bookrentalsystem.controller;

import com.abhiyan.bookrentalsystem.converter.CategoryDtoConverter;
import com.abhiyan.bookrentalsystem.dto.CategoryDto;
import com.abhiyan.bookrentalsystem.dto.ResponseDto;
import com.abhiyan.bookrentalsystem.model.Category;
import com.abhiyan.bookrentalsystem.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                                 BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            model.addAttribute("categoryDto",categoryDto);
            return "category/addCategory";
        }

        ResponseDto responseDto = categoryService.saveCategory(categoryDto);
        if(responseDto.getStatus()){
            redirectAttributes.addFlashAttribute("message",responseDto.getMessage());
            return "redirect:/view-categories";
        }
        model.addAttribute("errorMessage",responseDto.getMessage());
        return "category/addCategory";
    }

    @GetMapping("/view-categories")
    public String viewAllCategories(Model model){
        List<Category> categories = categoryService.viewCategories();
        List<CategoryDto> categoryDto = categoryDtoConverter.entityToDto(categories);
        model.addAttribute("category",categoryDto);
        return "category/viewCategory";
    }

    @GetMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        categoryService.deleteCategory(id);
        redirectAttributes.addFlashAttribute("message","Category deleted successfully.");
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
                                 BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            model.addAttribute("category", categoryDto);
            return "category/updateCategory";
        }
        categoryService.updateCategory(id,categoryDto);
        redirectAttributes.addFlashAttribute("message","Category updated successfully.");
        return "redirect:/view-categories";

    }

    @PostMapping("/search-category")
    public String searchCategory(@RequestParam(value="categoryName", required=true) String categoryName, Model model){
        List<Category> foundCategory = categoryService.searchCategory(categoryName);
        model.addAttribute("category",foundCategory);
        return "category/viewCategory";
    }




}
