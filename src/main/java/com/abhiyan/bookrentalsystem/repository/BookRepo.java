package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookRepo extends JpaRepository<Book, Integer> {

//    @Modifying
//    @Query(nativeQuery = true, value = "delete \n" +
//            "from author_book \n" +
//            "inner join author on author.id=author_book.author_id \n" +
//            "where author_book.author_id = ?1\n")
//    void deleteAuthorBookByAuthorId(int aid);
//
//        @Modifying
//    @Query(nativeQuery = true, value = "delete \n" +
//            "from book \n" +
//            "inner join author_book on book.id=author_book.book_id \n" +
//            "where author_book.author_id = ?1\n")
//    void deleteBookByAuthorId(int bid);

}


