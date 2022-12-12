package com.abhiyan.bookrentalsystem.service.transaction;


import com.abhiyan.bookrentalsystem.dto.TransactionDto;
import com.abhiyan.bookrentalsystem.model.Transaction;

import java.util.List;

public interface RentService {

    List<Transaction> viewRentTransaction();

    void rentBook(TransactionDto transactionDto);

    TransactionDto editRentBook(Integer id);

    TransactionDto updateRentBook(Integer id, TransactionDto transactionDto);

}
