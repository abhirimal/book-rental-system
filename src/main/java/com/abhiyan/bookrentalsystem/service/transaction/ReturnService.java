package com.abhiyan.bookrentalsystem.service.transaction;

import com.abhiyan.bookrentalsystem.dto.TransactionDto;
import com.abhiyan.bookrentalsystem.model.Transaction;

import java.util.List;

public interface ReturnService {

    List<Transaction> viewReturnTransaction();

    List<String> sendAllCode();

    Transaction sendRentData(String code);

    void returnBook(TransactionDto transactionDto);

    TransactionDto editReturnBook(Integer id);

    TransactionDto updateReturnBook(Integer id, TransactionDto transactionDto);
}
