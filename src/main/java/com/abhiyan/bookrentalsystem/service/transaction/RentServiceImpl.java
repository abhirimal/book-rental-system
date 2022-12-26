package com.abhiyan.bookrentalsystem.service.transaction;

import com.abhiyan.bookrentalsystem.dto.TransactionDto;
import com.abhiyan.bookrentalsystem.enums.RentType;
import com.abhiyan.bookrentalsystem.model.Book;
import com.abhiyan.bookrentalsystem.model.Member;
import com.abhiyan.bookrentalsystem.model.Transaction;
import com.abhiyan.bookrentalsystem.repository.BookRepo;
import com.abhiyan.bookrentalsystem.repository.MemberRepo;
import com.abhiyan.bookrentalsystem.repository.TransactionRepo;
import com.abhiyan.bookrentalsystem.service.services.GenerateTransactionCode;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    private final TransactionRepo transactionRepo;
    private final BookRepo bookRepo;
    private final MemberRepo memberRepo;

    public RentServiceImpl(TransactionRepo transactionRepo, BookRepo bookRepo, MemberRepo memberRepo) {
        this.transactionRepo = transactionRepo;
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
    }

    @Override
    public List<Transaction> viewRentTransaction() {
        return transactionRepo.findAllByRentType(RentType.RENT);
    }

    @Override
    public String rentBook(TransactionDto transactionDto) {
        Transaction transaction =new Transaction();

//        transaction.setCode(transactionDto.getCode());
        transaction.setNoOfDays(transactionDto.getNoOfDays());

        GenerateTransactionCode generateTransactionCode = new GenerateTransactionCode();
        String code = generateTransactionCode.generateTransactionCode();
        transaction.setCode(code);

        LocalDate fromDate = LocalDate.now();
        transaction.setFromDate(fromDate);

        LocalDate toDate = fromDate.plusDays(transaction.getNoOfDays());
        transaction.setToDate(toDate);

        Book book = bookRepo.findById(transactionDto.getBookId()).orElse(null);
        transaction.setBook(book);

        Member member = memberRepo.findById(transactionDto.getMemberId()).orElse(null);
        transaction.setMember(member);

        transaction.setRentType(RentType.RENT);

        transactionRepo.save(transaction);


        // decreasing stock count when rent
        Book book1 = bookRepo.findById(transactionDto.getBookId()).orElse(null);
        Integer stock = book1.getStockCount();
        stock = stock-1;
        book1.setStockCount(stock);
        bookRepo.save(book1);

        return code;

    }

    @Override
    public TransactionDto editRentBook(Integer id) {
        return null;
    }

    @Override
    public TransactionDto updateRentBook(Integer id, TransactionDto transactionDto) {
        return null;
    }

    // rent book by customer with customer username

    public String rentBookByCustomer(Integer id,TransactionDto transactionDto, String username) {
        Transaction transaction =new Transaction();

        transaction.setNoOfDays(transactionDto.getNoOfDays());

        GenerateTransactionCode generateTransactionCode = new GenerateTransactionCode();
        String code = generateTransactionCode.generateTransactionCode();
        transaction.setCode(code);

        LocalDate fromDate = LocalDate.now();
        transaction.setFromDate(fromDate);

        LocalDate toDate = fromDate.plusDays(transaction.getNoOfDays());
        transaction.setToDate(toDate);

        Book book = bookRepo.findById(id).orElse(null);
        transaction.setBook(book);

        Member member = memberRepo.findMemberByUsername(username);
        transaction.setMember(member);

        transaction.setRentType(RentType.RENT);

        transactionRepo.save(transaction);


        // decreasing stock count when rent
        Book book1 = bookRepo.findById(id).orElse(null);
        Integer stock = book1.getStockCount();
        stock = stock-1;
        book1.setStockCount(stock);
        bookRepo.save(book1);

        return code;

    }

}
