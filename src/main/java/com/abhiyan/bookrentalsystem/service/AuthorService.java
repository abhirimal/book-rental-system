package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.dto.AuthorDto;
import com.abhiyan.bookrentalsystem.dto.ResponseDto;
import com.abhiyan.bookrentalsystem.model.Author;

import java.util.List;

public interface AuthorService {

    ResponseDto saveAuthorDetails(AuthorDto authorDto);

    List<Author> getAllAuthors();

    AuthorDto editAuthor(Integer id);


    AuthorDto updateAuthor(Integer id, AuthorDto authorDto);

    void deleteAuthorById(Integer id);

    List<Author> searchAuthor(String authorName);
}
