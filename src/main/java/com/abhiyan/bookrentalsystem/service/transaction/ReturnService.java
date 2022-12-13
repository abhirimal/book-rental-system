package com.abhiyan.bookrentalsystem.service.transaction;

import com.abhiyan.bookrentalsystem.dto.ReturnResponseDto;
import com.abhiyan.bookrentalsystem.dto.TransactionDto;
import com.abhiyan.bookrentalsystem.model.Transaction;

import java.util.List;

public interface ReturnService {

    List<Transaction> viewAllReturnHistory();

    Transaction viewReturnTransaction(String code);

    List<String> sendAllCode();

    void confirmReturnTransaction(String code);


}
