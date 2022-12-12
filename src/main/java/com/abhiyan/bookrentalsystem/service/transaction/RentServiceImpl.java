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
    public List<TransactionDto> viewRentTransaction() {
        return null;
    }

    @Override
    public void rentBook(TransactionDto transactionDto) {
        Transaction transaction =new Transaction();

        transaction.setCode(transactionDto.getCode());
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

    }

    @Override
    public TransactionDto editRentBook(Integer id) {
        return null;
    }

    @Override
    public TransactionDto updateRentBook(Integer id, TransactionDto transactionDto) {
        return null;
    }

}
