package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.converter.BookDtoConverter;
import com.abhiyan.bookrentalsystem.dto.BookDto;
import com.abhiyan.bookrentalsystem.model.Book;
import com.abhiyan.bookrentalsystem.model.Category;
import com.abhiyan.bookrentalsystem.repository.BookRepo;
import com.abhiyan.bookrentalsystem.repository.CategoryRepo;
import com.abhiyan.bookrentalsystem.service.services.StringToDate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final BookDtoConverter bookDtoConverter;

    private final CategoryRepo categoryRepo;

    public BookServiceImpl(BookRepo bookRepo, BookDtoConverter bookDtoConverter, CategoryRepo categoryRepo) {
        this.bookRepo = bookRepo;
        this.bookDtoConverter = bookDtoConverter;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void saveBookDetails(BookDto bookDto) throws ParseException {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setNoOfPages(bookDto.getNoOfPages());
        book.setISBN(bookDto.getISBN());
        book.setRating(bookDto.getRating());

        StringToDate sDate = new StringToDate();
        LocalDate date = sDate.StringToDate(bookDto.getPublishedDate());
        book.setPublishedDate(date);

        book.setPhoto(bookDto.getPhoto());
        book.setStockCount(bookDto.getStockCount());
        System.out.println(bookDto.getCategoryId());
        Category category = categoryRepo.findById(bookDto.getCategoryId()).orElse(null);
        book.setCategory(category);
        bookRepo.save(book);
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
        bookRepo.deleteById(id);
    }
}
