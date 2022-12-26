package com.abhiyan.bookrentalsystem.controller.user;

import com.abhiyan.bookrentalsystem.dto.BookDto;
import com.abhiyan.bookrentalsystem.dto.TransactionDto;
import com.abhiyan.bookrentalsystem.model.Book;
import com.abhiyan.bookrentalsystem.model.Transaction;
import com.abhiyan.bookrentalsystem.service.impl.BookServiceImpl;
import com.abhiyan.bookrentalsystem.service.transaction.RentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    private final BookServiceImpl bookService;

    private final RentServiceImpl rentService;

    public UserController(BookServiceImpl bookService, RentServiceImpl rentService) {
        this.bookService = bookService;
        this.rentService = rentService;
    }

    @GetMapping("/view-book")
    public String viewAllBook(Model model){
        List<BookDto> books = bookService.findAllBooksWithStock();
        model.addAttribute("book",books);
        return  "user/viewBook";
    }

    @GetMapping("/view-book-details/{id}")
    public String viewBookDetail(@PathVariable int id, Model model) throws IOException {
        model.addAttribute("book", bookService.viewBookDetail(id));
        return "user/bookDetails";
    }

    @GetMapping("/rent-book/{id}")
    public String rentBook(@PathVariable int id, Model model){
        Transaction transaction = new Transaction();
        Book book = bookService.findBookById(id);
        model.addAttribute("book",book);
        model.addAttribute("rent",transaction);
        return "user/rentBook";
    }

    @PostMapping("/rent-book/new/{id}")
    public String rentNewBook(@PathVariable int id, @Valid @ModelAttribute("rent") TransactionDto rent,
                              BindingResult bindingResult, Model model, Principal principal){

        String username = principal.getName();
        String code = rentService.rentBookByCustomer(id,rent,username);
        model.addAttribute("code", code);

        return "user/displayCode";
    }


}
