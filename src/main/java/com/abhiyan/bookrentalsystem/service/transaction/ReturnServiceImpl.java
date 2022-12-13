package com.abhiyan.bookrentalsystem.service.transaction;

import com.abhiyan.bookrentalsystem.dto.ReturnResponseDto;
import com.abhiyan.bookrentalsystem.dto.TransactionDto;
import com.abhiyan.bookrentalsystem.enums.RentType;
import com.abhiyan.bookrentalsystem.model.Transaction;
import com.abhiyan.bookrentalsystem.repository.TransactionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnServiceImpl implements ReturnService{

    private final TransactionRepo transactionRepo;

    public ReturnServiceImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
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
    }



}
