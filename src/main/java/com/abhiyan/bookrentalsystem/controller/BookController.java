package com.abhiyan.bookrentalsystem.controller;

import com.abhiyan.bookrentalsystem.converter.AuthorDtoConverter;
import com.abhiyan.bookrentalsystem.converter.CategoryDtoConverter;
import com.abhiyan.bookrentalsystem.dto.AuthorDto;
import com.abhiyan.bookrentalsystem.dto.BookDto;
import com.abhiyan.bookrentalsystem.dto.CategoryDto;
import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.model.Category;
import com.abhiyan.bookrentalsystem.repository.BookRepo;
import com.abhiyan.bookrentalsystem.service.AuthorServiceImpl;
import com.abhiyan.bookrentalsystem.service.BookServiceImpl;
import com.abhiyan.bookrentalsystem.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Controller
public class BookController {

    private final BookServiceImpl bookService;
    private final AuthorServiceImpl authorService;
    private final CategoryService categoryService;
    private final AuthorDtoConverter authorDtoConverter;
    private final CategoryDtoConverter categoryDtoConverter;

    public BookController(BookServiceImpl bookService, BookRepo bookRepo, AuthorServiceImpl authorService, CategoryService categoryService, AuthorDtoConverter authorDtoConverter, CategoryDtoConverter categoryDtoConverter) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.authorDtoConverter = authorDtoConverter;
        this.categoryDtoConverter = categoryDtoConverter;
    }

    @GetMapping("/add-book")
    public String addBook(Model model){
        BookDto book = new BookDto();
        List<Author> auth = authorService.getAllAuthors();
        List<AuthorDto> authorDto = authorDtoConverter.entityToDto(auth);
        List<Category> categories = categoryService.viewCategories();
        List<CategoryDto> categoryDto = categoryDtoConverter.entityToDto(categories);

        model.addAttribute("authorDto",authorDto);
        model.addAttribute("categoryDto",categoryDto);
        model.addAttribute("book", book);
        return "book/addBook";
    }

    @PostMapping("/add-book/new")
    public String addNewBook(@Valid @ModelAttribute("book") BookDto book, BindingResult bindingResult,
                             Model model) throws ParseException {

        if(bindingResult.hasErrors()){
            System.out.println("Something went wrong");
            List<Author> auth = authorService.getAllAuthors();
            List<AuthorDto> authorDto = authorDtoConverter.entityToDto(auth);
            List<Category> categories = categoryService.viewCategories();
            List<CategoryDto> categoryDto = categoryDtoConverter.entityToDto(categories);

            model.addAttribute("authorDto",authorDto);
            model.addAttribute("categoryDto",categoryDto);
            model.addAttribute("book",book);
            return "/book/addBook";
        }
        System.out.println("I am here.");
        System.out.println(book);
        bookService.saveBookDetails(book);
        return "redirect:/view-books";
    }

    @GetMapping("/view-books")
    public String viewAllBooks(Model model){
        List<BookDto> bookDto = bookService.getAllBooks();
        model.addAttribute("book", bookDto);
        return "book/viewBook";
    }

    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable Integer id){
        bookService.deleteBookById(id);
        return "redirect:/view-books";
    }

    @GetMapping("/edit-book/{id}")
    public String editBook(@PathVariable Integer id, Model model){
        BookDto bookDto = bookService.editBook(id);
        model.addAttribute("bookDto",bookDto);

        List<Author> auth = authorService.getAllAuthors();
        List<AuthorDto> authorDto = authorDtoConverter.entityToDto(auth);
        List<Category> categories = categoryService.viewCategories();
        List<CategoryDto> categoryDto = categoryDtoConverter.entityToDto(categories);

        model.addAttribute("authorDto",authorDto);
        model.addAttribute("categoryDto",categoryDto);

        return "book/updateBook";
    }

    @PostMapping("/update-book/{id}")
    public String updateBook(@PathVariable Integer id,@Valid @ModelAttribute("bookDto") BookDto bookDto,
                             BindingResult bindingResult, Model model) throws ParseException {

        if ((bindingResult.hasErrors())){
            model.addAttribute("bookDto",bookDto);
            List<Author> auth = authorService.getAllAuthors();
            List<AuthorDto> authorDto = authorDtoConverter.entityToDto(auth);
            List<Category> categories = categoryService.viewCategories();
            List<CategoryDto> categoryDto = categoryDtoConverter.entityToDto(categories);

            model.addAttribute("authorDto",authorDto);
            model.addAttribute("categoryDto",categoryDto);
            return "book/updateBook";
        }
        bookService.updateBook(id,bookDto);
        return "redirect:/view-books";
    }
}
