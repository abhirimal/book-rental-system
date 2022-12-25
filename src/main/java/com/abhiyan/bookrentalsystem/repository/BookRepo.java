package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Integer> {

    @Query(nativeQuery = true, value = "select * from tbl_book \n" +
            "where stock_count>=1 and account_state='ACTIVE'")
    List<Book> findAllBookWithStockAndActiveState();



}


