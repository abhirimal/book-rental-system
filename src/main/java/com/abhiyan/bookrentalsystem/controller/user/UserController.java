package com.abhiyan.bookrentalsystem.controller.user;

import com.abhiyan.bookrentalsystem.dto.BookDto;
import com.abhiyan.bookrentalsystem.dto.TransactionDto;
import com.abhiyan.bookrentalsystem.model.Book;
import com.abhiyan.bookrentalsystem.model.Transaction;
import com.abhiyan.bookrentalsystem.service.impl.BookServiceImpl;
import com.abhiyan.bookrentalsystem.service.transaction.RentServiceImpl;
import com.abhiyan.bookrentalsystem.service.transaction.ReturnServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    private final BookServiceImpl bookService;

    private final RentServiceImpl rentService;

    private final ReturnServiceImpl returnService;

    public UserController(BookServiceImpl bookService, RentServiceImpl rentService, ReturnServiceImpl returnService) {
        this.bookService = bookService;
        this.rentService = rentService;
        this.returnService = returnService;
    }

    @GetMapping("/view-book")
    public String viewAllBook(Model model){
        List<BookDto> books = bookService.findAllBooksWithStock();
        model.addAttribute("book",books);
        return  "user/book/viewBook";
    }

    @GetMapping("/view-book-details/{id}")
    public String viewBookDetail(@PathVariable int id, Model model) throws IOException {
        model.addAttribute("book", bookService.viewBookDetail(id));
        return "user/book/bookDetails";
    }

    @GetMapping("/rent-book/{id}")
    public String rentBook(@PathVariable int id, Model model){
        Transaction transaction = new Transaction();
        Book book = bookService.findBookById(id);
        model.addAttribute("book",book);
        model.addAttribute("rent",transaction);
        return "user/rent/rentBook";
    }

    @PostMapping("/rent-book/new/{id}")
    public String rentNewBook(@PathVariable int id, @Valid @ModelAttribute("rent") TransactionDto rent,
                              BindingResult bindingResult, Model model, Principal principal){

        String username = principal.getName();
        String code = rentService.rentBookByCustomer(id,rent,username);
        model.addAttribute("code", code);

        return "user/rent/displayCode";
    }

    @GetMapping("/rented-book-list")
    public String rentedBookList(Model model, Principal principal){
        String username = principal.getName();
        List<Transaction> transactions = returnService.viewRentedBookListOfUser(username);
        model.addAttribute("transaction",transactions);
        return "user/return/rentedBookList";
    }

    @GetMapping("/return-book/{id}")
    public String returnBook(@PathVariable int id,Model model, RedirectAttributes redirectAttributes){
        Transaction transaction = returnService.viewTransactionByTransactionId(id);
        model.addAttribute("transaction",transaction);
        return "user/return/confirmBookReturn";
    }

    @PostMapping("/confirm-return-book/{id}")
    public String confirmReturnBook(@PathVariable int id, RedirectAttributes redirectAttributes){
        returnService.returnBookByTransactionId(id);
        redirectAttributes.addFlashAttribute("message","Book returned successfully.");
        return "redirect:/user/returned-book-history";
    }

    @GetMapping("/returned-book-history")
    public String returnedBookHistory(Model model,Principal principal){
        String username = principal.getName();
        List<Transaction> transaction = returnService.viewReturnTransactionByUsername(username);
        model.addAttribute("transaction",transaction);
        return "user/return/returnedBookList";
    }




}
