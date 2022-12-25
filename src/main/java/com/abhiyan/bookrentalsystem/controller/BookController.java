package com.abhiyan.bookrentalsystem.controller;

import com.abhiyan.bookrentalsystem.converter.AuthorDtoConverter;
import com.abhiyan.bookrentalsystem.converter.CategoryDtoConverter;
import com.abhiyan.bookrentalsystem.dto.AuthorDto;
import com.abhiyan.bookrentalsystem.dto.BookDto;
import com.abhiyan.bookrentalsystem.dto.CategoryDto;
import com.abhiyan.bookrentalsystem.dto.ResponseDto;
import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.model.Book;
import com.abhiyan.bookrentalsystem.model.Category;
import com.abhiyan.bookrentalsystem.repository.BookRepo;
import com.abhiyan.bookrentalsystem.service.impl.AuthorServiceImpl;
import com.abhiyan.bookrentalsystem.service.impl.BookServiceImpl;
import com.abhiyan.bookrentalsystem.service.CategoryService;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
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
                             Model model, RedirectAttributes redirectAttributes) throws ParseException, IOException {

        List<Author> auth = authorService.getAllAuthors();
        List<AuthorDto> authorDto = authorDtoConverter.entityToDto(auth);
        List<Category> categories = categoryService.viewCategories();
        List<CategoryDto> categoryDto = categoryDtoConverter.entityToDto(categories);

        if(bindingResult.hasErrors()){
            System.out.println("Something went wrong");
            model.addAttribute("authorDto",authorDto);
            model.addAttribute("categoryDto",categoryDto);
            model.addAttribute("book",book);
            return "/book/addBook";
        }
//        System.out.println("I am here.");
        System.out.println(book);
        ResponseDto responseDto = bookService.saveBookDetails(book);
        if(responseDto.getStatus()){
            redirectAttributes.addFlashAttribute("message",responseDto.getMessage());
            return "redirect:/view-books";
        }
        model.addAttribute("errorMessage",responseDto.getMessage());
        model.addAttribute("authorDto",authorDto);
        model.addAttribute("categoryDto",categoryDto);
        model.addAttribute("book",book);
        return "book/addBook";
    }

    @GetMapping("/view-books")
    public String viewAllBooks(Model model){
        List<BookDto> bookDto = bookService.getAllBooks();
        model.addAttribute("book", bookDto);
        return "book/viewBook";
    }

    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        bookService.deleteBookById(id);
        redirectAttributes.addFlashAttribute("message","Book deleted successfully.");
        return "redirect:/view-books";
    }

    @GetMapping("/edit-book/{id}")
    public String editBook(@PathVariable Integer id, Model model){
        BookDto bookDto = bookService.editBook(id);
        List<Author> auth = authorService.getAllAuthors();
        List<AuthorDto> authorDto = authorDtoConverter.entityToDto(auth);
        List<Category> categories = categoryService.viewCategories();
        List<CategoryDto> categoryDto = categoryDtoConverter.entityToDto(categories);

        model.addAttribute("authorDto",authorDto);
        model.addAttribute("categoryDto",categoryDto);
        model.addAttribute("bookDto",bookDto);


        return "book/updateBook";
    }

    @PostMapping("/update-book/{id}")
    public String updateBook(@PathVariable Integer id,@Valid @ModelAttribute("bookDto") BookDto bookDto,
                             BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws ParseException, IOException {
        System.out.println("inside update controller");
        if ((bindingResult.hasErrors())){
            List<Author> auth = authorService.getAllAuthors();
            List<AuthorDto> authorDto = authorDtoConverter.entityToDto(auth);
            List<Category> categories = categoryService.viewCategories();
            List<CategoryDto> categoryDto = categoryDtoConverter.entityToDto(categories);

            model.addAttribute("authorDto",authorDto);
            model.addAttribute("categoryDto",categoryDto);
            model.addAttribute("bookDto",bookDto);
            return "book/updateBook";
        }
        bookService.updateBook(id,bookDto);
        redirectAttributes.addFlashAttribute("message","Book data updated successfully.");
        return "redirect:/view-books";
    }

    @GetMapping("/view-book-details/{id}")
    public String viewBookDetailById(@PathVariable int id, Model model) throws IOException {
        BookDto book = bookService.viewBookDetail(id);
        model.addAttribute("book",book);
        return "book/bookDetails";
    }

    @PostMapping("/search-book")
    public String searchStudent(@RequestParam(value="bookName", required=true) String bookName, Model model){
        List<Book> foundBook = bookService.searchBook(bookName);
        model.addAttribute("book",foundBook);
        return "book/viewBook";
    }
}
