package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.model.Author;

import java.util.List;

public interface AuthorService {

    void saveAuthorDetails(Author author);

    List<Author> getAllAuthors();

    Author editAuthor(Integer id);


    Author updateAuthor(Integer id, Author author);

    void deleteAuthorById(Integer id);
}
