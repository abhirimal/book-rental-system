package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepo extends JpaRepository<Book, Integer> {

    @Query(nativeQuery = true, value = "select * from tbl_book \n" +
            "where account_state='ACTIVE'")
    List<Book> findAllBookWithActiveState();
    @Query(nativeQuery = true, value = "select * from tbl_book \n" +
            "where stock_count>=1 and account_state='ACTIVE'")
    List<Book> findAllBookWithStockAndActiveState();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE tbl_book set account_state='DELETED' " +
            "where id=?1 ")
    void softDeleteBookById(int id);

    @Query(nativeQuery = true, value = "SELECT * from tbl_book where isbn=?1 and " +
            "account_state='DELETED' ")
    Book findDeletedStateBook(Long isbn);

    @Query(nativeQuery = true, value = "select *\n" +
            "from tbl_book b\n" +
            "where account_state='ACTIVE' and lower(b.name) like concat(lower(?1), '%')")
    List<Book> findAllByName(String bookName);

}


