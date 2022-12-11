package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.converter.BookDtoConverter;
import com.abhiyan.bookrentalsystem.dto.AuthorDto;
import com.abhiyan.bookrentalsystem.dto.BookDto;
import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.model.Book;
import com.abhiyan.bookrentalsystem.repository.BookRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final BookDtoConverter bookDtoConverter;

    public BookServiceImpl(BookRepo bookRepo, BookDtoConverter bookDtoConverter) {
        this.bookRepo = bookRepo;
        this.bookDtoConverter = bookDtoConverter;
    }

    @Override
    public void saveBookDetails(BookDto book) {
        Book book1 = bookDtoConverter.dtoToEntity(book);
        bookRepo.save(book1);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> book = bookRepo.findAll();
        List<BookDto> bookDto = bookDtoConverter.entityToDto(book);
        return bookDto;
    }

    @Override
    public BookDto editBook(Integer id) {
        return null;
    }

    @Override
    public BookDto updateBook(Integer id, BookDto bookDto) {
        return null;
    }

    @Override
    public void deleteBookById(Integer id) {

    }
}
