package com.abhiyan.bookrentalsystem.controller;

import com.abhiyan.bookrentalsystem.dto.TransactionDto;
import com.abhiyan.bookrentalsystem.model.Transaction;
import com.abhiyan.bookrentalsystem.service.transaction.ReturnService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.table.TableRowSorter;
import java.util.List;

@Controller
public class ReturnController {

    private final ReturnService returnService;

    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

//    @GetMapping("/return-book")
//    public String returnBook(Model model){
//        TransactionDto transactionDto = new TransactionDto();
//        model.addAttribute("trans",transactionDto);
//        return "returnBook/returnBook";
//    }
//
//    @PostMapping("/return-book/new")
//    public String returnTransactionData(@ModelAttribute TransactionDto transactionDto,
//                                        ){
//
//        return "returnBook/confirmReturn";
//    }

    @GetMapping("/return-book")
    public String returnBook(Model model){
        List<String> code = returnService.sendAllCode();
        model.addAttribute("code",code);
        return "returnBook/returnBook";
    }

    @PostMapping ("/confirm-return-book")
    public String confirmReturn(@RequestParam(value="code", required=true) String code, Model model){
        Transaction transaction = returnService.viewReturnTransaction(code);
//        System.out.println(code);
        model.addAttribute("transaction",transaction);
        return "returnBook/confirmReturn";

    }

    @PostMapping ("/book-returned")
    public String bookReturned(@RequestParam(value="code", required=true) String code){
        returnService.confirmReturnTransaction(code);
//        System.out.println(code);
        return "returnBook/returnBook";

    }
}
