package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {
}
