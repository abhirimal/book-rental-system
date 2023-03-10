package com.abhiyan.bookrentalsystem.controller.admin;

import com.abhiyan.bookrentalsystem.dto.TransactionDto;
import com.abhiyan.bookrentalsystem.model.Transaction;
import com.abhiyan.bookrentalsystem.service.transaction.ReturnService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.table.TableRowSorter;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RequestMapping("/admin")
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
    public String returnBook(Model model) {
        List<String> code = returnService.sendAllCode();
        model.addAttribute("code", code);
        return "admin/returnBook/returnBook";
    }

    @PostMapping("/confirm-return-book")
    public String confirmReturn(@RequestParam(value="code") String code, Model model) {
//        if(bindingResult.hasErrors()){
//            return "returnBook/returnBook";
//        }
        Transaction transaction = returnService.viewReturnTransaction(code);
//      System.out.println(code);
        model.addAttribute("transaction", transaction);
        return "admin/returnBook/confirmReturn";

    }

    @PostMapping("/book-returned")
    public String bookReturned(@RequestParam("code") String code, RedirectAttributes redirectAttributes) {
        returnService.confirmReturnTransaction(code);
        redirectAttributes.addFlashAttribute("message", "Book returned successfully.");

//        System.out.println(code);
        return "redirect:/admin/view-return-history";
    }

    @GetMapping("/view-return-history")
    public String viewRentHistory(Model model) {
        model.addAttribute("transaction", returnService.viewAllReturnHistory());
        return "admin/returnBook/viewReturn";
    }
}
