package com.abhiyan.bookrentalsystem.controller.admin;

import com.abhiyan.bookrentalsystem.converter.AuthorDtoConverter;
import com.abhiyan.bookrentalsystem.dto.AuthorDto;
import com.abhiyan.bookrentalsystem.dto.ResponseDto;
import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.model.Category;
import com.abhiyan.bookrentalsystem.service.impl.AuthorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AuthorController {

    private final AuthorServiceImpl authorService;

    private final AuthorDtoConverter authorDtoConverter;

    public AuthorController(AuthorServiceImpl authorService, AuthorDtoConverter authorDtoConverter) {
        this.authorService = authorService;
        this.authorDtoConverter = authorDtoConverter;
    }


    @GetMapping("/save-author")
    public String saveAuthor(Model model){
        AuthorDto authorDto = new AuthorDto();
        model.addAttribute("authorDto", authorDto);
        return "admin/author/registerAuthor";
    }

    @PostMapping("/save-author/new")
    public String saveAuthor(@Valid @ModelAttribute("authorDto") AuthorDto authorDto,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if(bindingResult.hasErrors()){
//            System.out.println("Something went wrong");
//            bindingResult.getAllErrors().forEach(a-> System.out.println(a));
            model.addAttribute("authorDto",authorDto );
            return "admin/author/registerAuthor";
        }

        ResponseDto responseDto = authorService.saveAuthorDetails(authorDto);

        if(responseDto.getStatus()){
            redirectAttributes.addFlashAttribute("message",responseDto.getMessage());
            return "redirect:/admin/view-authors";
        }

        model.addAttribute("errorMessage",responseDto.getMessage());
        return "admin/author/registerAuthor";
    }


    @GetMapping("/view-authors")
    public String viewAuthors(Model model){
    List<Author> auth = authorService.getAllAuthors();
    List<AuthorDto> authorDto = authorDtoConverter.entityToDto(auth);
    model.addAttribute("author",authorDto);
    return "admin/author/viewAuthors";
    }

    @GetMapping("/edit-author/{id}")
    public String editAuthor(Model model, @PathVariable int id){
        model.addAttribute("author",authorService.editAuthor(id));
        return "admin/author/updateAuthor";
    }

    @PostMapping("/update-author/{id}")
    public String updateAuthor(Model model, @PathVariable int id,@Valid @ModelAttribute("author") AuthorDto authorDto,BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            model.addAttribute("author",authorDto);
            return "admin/author/updateAuthor";
        }
        authorService.updateAuthor(id,authorDto);
        redirectAttributes.addFlashAttribute("message","Account updated successfully.");
        return "redirect:/admin/view-authors";
    }

    @GetMapping("/delete-author/{id}")
    public String deleteAuthorById(@PathVariable int id, RedirectAttributes redirectAttributes){
        authorService.deleteAuthorById(id);
        redirectAttributes.addFlashAttribute("message","Account deleted successfully.");
        return "redirect:/admin/view-authors";
    }

    @PostMapping("/search-author")
    public String searchAuthor(@RequestParam(value="authorName", required=true) String authorName, Model model){
        List<Author> foundAuthor = authorService.searchAuthor(authorName);
        model.addAttribute("author",foundAuthor);
        return "admin/author/viewAuthors";
    }

}
