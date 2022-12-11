package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.dto.AuthorDto;
import com.abhiyan.bookrentalsystem.dto.BookDto;
import com.abhiyan.bookrentalsystem.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public void saveBookDetails(BookDto bookDto) {

    }

    @Override
    public List<Author> getAllBooks() {
        return null;
    }

    @Override
    public AuthorDto editBook(Integer id) {
        return null;
    }

    @Override
    public AuthorDto updateBook(Integer id, BookDto bookDto) {
        return null;
    }

    @Override
    public void deleteBookById(Integer id) {

    }
}
