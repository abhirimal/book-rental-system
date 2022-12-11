package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.dto.AuthorDto;
import com.abhiyan.bookrentalsystem.dto.BookDto;
import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.model.Book;

import java.util.List;

public interface BookService {


    void saveBookDetails(BookDto book);

    List<BookDto> getAllBooks();

    BookDto editBook(Integer id);


    BookDto updateBook(Integer id, BookDto bookDto);

    void deleteBookById(Integer id);
}