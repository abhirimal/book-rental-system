package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.dto.AuthorDto;
import com.abhiyan.bookrentalsystem.dto.BookDto;
import com.abhiyan.bookrentalsystem.dto.ResponseDto;
import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.model.Book;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface BookService {


    ResponseDto saveBookDetails(BookDto book) throws ParseException, IOException;

    List<BookDto> getAllBooks();

    BookDto editBook(Integer id);


    BookDto updateBook(Integer id, BookDto bookDto) throws ParseException, IOException;

    void deleteBookById(Integer id);

    List<BookDto> findAllBooksWithStock();

    BookDto viewBookDetail(int id) throws IOException;

    List<Book> searchBook(String bookName);

    Book findBookById(int id);
}
