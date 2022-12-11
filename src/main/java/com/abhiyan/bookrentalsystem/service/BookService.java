package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.dto.AuthorDto;
import com.abhiyan.bookrentalsystem.dto.BookDto;
import com.abhiyan.bookrentalsystem.model.Author;

import java.util.List;

public interface BookService {


    void saveBookDetails(BookDto bookDto);

    List<Author> getAllBooks();

    AuthorDto editBook(Integer id);


    AuthorDto updateBook(Integer id, BookDto bookDto);

    void deleteBookById(Integer id);
}
