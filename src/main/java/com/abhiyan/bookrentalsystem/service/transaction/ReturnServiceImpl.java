package com.abhiyan.bookrentalsystem.service.transaction;

import com.abhiyan.bookrentalsystem.enums.RentType;
import com.abhiyan.bookrentalsystem.model.Book;
import com.abhiyan.bookrentalsystem.model.Transaction;
import com.abhiyan.bookrentalsystem.repository.BookRepo;
import com.abhiyan.bookrentalsystem.repository.TransactionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnServiceImpl implements ReturnService{

    private final TransactionRepo transactionRepo;
    private final BookRepo bookRepo;

    public ReturnServiceImpl(TransactionRepo transactionRepo, BookRepo bookRepo) {
        this.transactionRepo = transactionRepo;
        this.bookRepo = bookRepo;
    }


    @Override
    public List<Transaction> viewAllReturnHistory() {
        return transactionRepo.findAllByRentType(RentType.RETURN);
    }

    @Override
    public Transaction viewReturnTransaction(String code) {
        return transactionRepo.findTransactionByCode(code);
    }

    @Override
    public List<String> sendAllCode() {
        return transactionRepo.allCode();
    }

    @Override
    public void confirmReturnTransaction(String code) {
        Transaction transaction = transactionRepo.findTransactionByCode(code);
        transaction.setRentType(RentType.RETURN);
        transactionRepo.save(transaction);

        //increase stock count on return
        Book book = bookRepo.findById(transaction.getBook().getId()).orElse(null);
        Integer stock = book.getStockCount();
        stock=stock+1;
        book.setStockCount(stock);
        bookRepo.save(book);
    }



}
