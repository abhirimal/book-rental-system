package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.dto.AuthorDto;
import com.abhiyan.bookrentalsystem.model.Author;

import java.util.List;

public interface AuthorService {

    void saveAuthorDetails(AuthorDto authorDto);

    List<Author> getAllAuthors();

    Author editAuthor(Integer id);


    AuthorDto updateAuthor(Integer id, AuthorDto authorDto);

    void deleteAuthorById(Integer id);
}
