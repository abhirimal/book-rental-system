package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.dto.AuthorDto;
import com.abhiyan.bookrentalsystem.dto.BookDto;
import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.model.Book;

import java.text.ParseException;
import java.util.List;

public interface BookService {


    void saveBookDetails(BookDto book) throws ParseException;

    List<BookDto> getAllBooks();

    BookDto editBook(Integer id);


    BookDto updateBook(Integer id, BookDto bookDto) throws ParseException;

    void deleteBookById(Integer id);

    List<BookDto> findAllBooksWithStock();
}
