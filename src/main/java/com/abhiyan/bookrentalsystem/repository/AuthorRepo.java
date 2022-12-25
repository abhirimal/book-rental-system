package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.model.Book;
import com.abhiyan.bookrentalsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {
    Optional<Object> findByEmail(String email);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value="UPDATE author set account_state='DELETED' where id=?1")
    void softDeleteAuthorById(int id);

    @Query(nativeQuery = true, value = "SELECT * from author where account_state='ACTIVE'")
    List<Author> findAllActiveAuthor();

    @Query(nativeQuery = true, value = "SELECT * from author where email=?1 and " +
            "account_state='DELETED'")
    Author findDeletedStateAuthor(String name);

    @Query(nativeQuery = true, value = "select *\n" +
            "from author a\n" +
            "where account_state='ACTIVE' and lower(a.name) like concat(lower(?1), '%')")
    List<Author> findAllByName(String authorName);

}
