package com.abhiyan.bookrentalsystem.service.transaction;


import com.abhiyan.bookrentalsystem.dto.TransactionDto;

import java.util.List;

public interface RentService {

    List<TransactionDto> viewRentTransaction();

    void rentBook(TransactionDto transactionDto);

    TransactionDto editRentBook(Integer id);

    TransactionDto updateRentBook(Integer id, TransactionDto transactionDto);

}
