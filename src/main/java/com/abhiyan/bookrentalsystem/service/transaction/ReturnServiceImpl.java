package com.abhiyan.bookrentalsystem.service.transaction;

import com.abhiyan.bookrentalsystem.enums.RentType;
import com.abhiyan.bookrentalsystem.model.Book;
import com.abhiyan.bookrentalsystem.model.Member;
import com.abhiyan.bookrentalsystem.model.Transaction;
import com.abhiyan.bookrentalsystem.repository.BookRepo;
import com.abhiyan.bookrentalsystem.repository.MemberRepo;
import com.abhiyan.bookrentalsystem.repository.TransactionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnServiceImpl implements ReturnService{

    private final TransactionRepo transactionRepo;
    private final BookRepo bookRepo;

    private final MemberRepo memberRepo;

    public ReturnServiceImpl(TransactionRepo transactionRepo, BookRepo bookRepo, MemberRepo memberRepo) {
        this.transactionRepo = transactionRepo;
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
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


    public List<Transaction> viewRentedBookListOfUser(String username){
        Member member = memberRepo.findMemberByUsername(username);
        Integer id = member.getId();

        List<Transaction> transaction = transactionRepo.findTransactionListByMemberId(id);
        return transaction;
    }

    public void returnBookByTransactionId(Integer id){
        Transaction transaction = transactionRepo.findById(id).orElse(null);
        transaction.setRentType(RentType.RETURN);
        transactionRepo.save(transaction);

        //increase stock count on return
        Book book = bookRepo.findById(transaction.getBook().getId()).orElse(null);
        Integer stock = book.getStockCount();
        stock=stock+1;
        book.setStockCount(stock);
        bookRepo.save(book);

    }

    public Transaction viewTransactionByTransactionId(Integer id){
        return transactionRepo.findById(id).orElse(null);
    }

    public List<Transaction> viewReturnTransactionByUsername(String username){
        Member member = memberRepo.findMemberByUsername(username);
        Integer id = member.getId();

        return transactionRepo.findReturnedBookTransactionListByMemberId(id);

    }

}
