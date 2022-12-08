package com.abhiyan.bookrentalsystem.controller;

import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.repository.AuthorRepo;
import com.abhiyan.bookrentalsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/save-author/new")
    public String saveAuthor(Model model){
        Author author = new Author();
        model.addAttribute("author", author);
        return "author/registerAuthor";
    }

    @PostMapping("/save-author")
    public String saveAuthor(Author author){
        authorService.saveAuthorDetails(author);
        return "redirect:/home";
    }


}
