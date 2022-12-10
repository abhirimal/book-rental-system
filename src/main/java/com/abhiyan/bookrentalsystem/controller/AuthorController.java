package com.abhiyan.bookrentalsystem.controller;

import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/save-author")
    public String saveAuthor(Model model){
        Author author = new Author();
        model.addAttribute("author", author);
        return "author/registerAuthor";
    }

    @PostMapping("/save-author/new")
    public String saveAuthor(@ModelAttribute Author author){
        authorService.saveAuthorDetails(author);
        return "redirect:/view-authors";
    }


    @GetMapping("/view-authors")
    public String viewAuthors(Model model){
    List<Author> auth = authorService.getAllAuthors();
    model.addAttribute("author",auth);
    return "author/viewAuthors";
    }

    @GetMapping("/edit-author/{id}")
    public String editAuthor(Model model, @PathVariable int id){
        model.addAttribute("author",authorService.editAuthor(id));
        return "author/updateAuthor";
    }

    @PostMapping("/update-author/{id}")
    public String updateAuthor(Model model, @PathVariable int id,@ModelAttribute Author author ){
        model.addAttribute("author",authorService.updateAuthor(id,author));
        return "redirect:/view-authors";
    }



    @GetMapping("/delete-author/{id}")
    public String deleteAuthorById(@PathVariable int id){
        authorService.deleteAuthorById(id);
        return "redirect:/view-authors";
    }


}
