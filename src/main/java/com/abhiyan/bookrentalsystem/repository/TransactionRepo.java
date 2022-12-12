package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {


}
