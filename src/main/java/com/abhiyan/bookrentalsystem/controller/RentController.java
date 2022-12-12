package com.abhiyan.bookrentalsystem.controller;

import com.abhiyan.bookrentalsystem.dto.BookDto;
import com.abhiyan.bookrentalsystem.dto.MemberDto;
import com.abhiyan.bookrentalsystem.dto.TransactionDto;
import com.abhiyan.bookrentalsystem.model.Member;
import com.abhiyan.bookrentalsystem.service.BookServiceImpl;
import com.abhiyan.bookrentalsystem.service.MemberServiceImpl;
import com.abhiyan.bookrentalsystem.service.transaction.RentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RentController {

    private final RentServiceImpl rentService;
    private final BookServiceImpl bookService;
    private final MemberServiceImpl memberService;

    public RentController(RentServiceImpl rentService, BookServiceImpl bookService, MemberServiceImpl memberService) {
        this.rentService = rentService;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @GetMapping("/view-rent-history")
    public String viewRentHistory(){
        return "rentBook/rentBook";
    }

    @GetMapping("rent-book")
    public String rentBook(Model model){
        TransactionDto transactionDto = new TransactionDto();
        List<MemberDto> member = memberService.viewMembers();
        List<BookDto> book = bookService.getAllBooks();
        model.addAttribute("book",book);
        model.addAttribute("member",member);
        model.addAttribute("rent",transactionDto);
        return "rentBook/rentBook";
    }

    @PostMapping("rent-book/new")
    public String rentNewBook(@ModelAttribute("rent") TransactionDto rent, BindingResult bindingResult,
                              Model model){

        if (bindingResult.hasErrors()){

            List<MemberDto> member = memberService.viewMembers();
            List<BookDto> book = bookService.getAllBooks();
            model.addAttribute("book",book);
            model.addAttribute("member",member);
            model.addAttribute("rent",rent);
            System.out.println("Inside br");
            return "rentBook/rentBook";
        }
        System.out.println("Outside br");
        rentService.rentBook(rent);
        return "book/viewBook";
    }


}
