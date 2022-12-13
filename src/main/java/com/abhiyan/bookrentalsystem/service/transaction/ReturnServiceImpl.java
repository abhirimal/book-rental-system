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

    @Override
    public List<ReturnResponseDto> idCode() {
        return null;
    }


    @Override
    public Transaction sendRentData(String code) {
        Transaction transaction = transactionRepo.findTransactionByCode(code);
        return transaction;
    }

    @Override
    public void returnBook(TransactionDto transactionDto) {

    }

    @Override
    public TransactionDto editReturnBook(Integer id) {
        return null;
    }

    @Override
    public TransactionDto updateReturnBook(Integer id, TransactionDto transactionDto) {
        return null;
    }
}
