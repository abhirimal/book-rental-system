package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {
    Optional<Object> findByEmail(String email);

//    @Query(nativeQuery = true, value = "select * from author \n" +
//            "where activeStatus=true")
//    List<Author> findAllActiveAuthour();
}
