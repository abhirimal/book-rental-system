package com.abhiyan.bookrentalsystem.service;

import com.abhiyan.bookrentalsystem.converter.BookDtoConverter;
import com.abhiyan.bookrentalsystem.dto.BookDto;
import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.model.Book;
import com.abhiyan.bookrentalsystem.model.Category;
import com.abhiyan.bookrentalsystem.repository.AuthorRepo;
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

    private final AuthorRepo authorRepo;

    public BookServiceImpl(BookRepo bookRepo, BookDtoConverter bookDtoConverter, CategoryRepo categoryRepo, AuthorRepo authorRepo) {
        this.bookRepo = bookRepo;
        this.bookDtoConverter = bookDtoConverter;
        this.categoryRepo = categoryRepo;
        this.authorRepo = authorRepo;
    }

    @Override
    public void saveBookDetails(BookDto bookDto) throws ParseException {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setNoOfPages(bookDto.getNoOfPages());
        book.setIsbn(bookDto.getIsbn());
        book.setRating(bookDto.getRating());

        StringToDate sDate = new StringToDate();
        LocalDate date = sDate.StringToDate(bookDto.getPublishedDate());
        book.setPublishedDate(date);

        book.setPhoto(bookDto.getPhoto());
        book.setStockCount(bookDto.getStockCount());

        Category category = categoryRepo.findById(bookDto.getCategoryId()).orElse(null);
        book.setCategory(category);

        List<Author> authors = authorRepo.findAllById(bookDto.getAuthorId());
        book.setAuthors(authors);
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
        Book book = bookRepo.findById(id).orElse(null);
        BookDto bookDto = bookDtoConverter.entityToDto(book);
        return bookDto;
    }

    @Override
    public BookDto updateBook(Integer id, BookDto bookDto) throws ParseException {
        Book book = bookRepo.findById(id).orElse(null);
        book.setName(bookDto.getName());
        book.setIsbn(bookDto.getIsbn());
        Category category = categoryRepo.findById(bookDto.getCategoryId()).orElse(null);
        System.out.println(bookDto.getCategoryId());
        book.setCategory(category);
        book.setRating(bookDto.getRating());
        book.setNoOfPages(bookDto.getNoOfPages());
        book.setPhoto(bookDto.getPhoto());
        book.setStockCount(bookDto.getStockCount());

        StringToDate sDate = new StringToDate();
        LocalDate date = sDate.StringToDate(bookDto.getPublishedDate());
        book.setPublishedDate(date);
        //        book.setAuthors(bookDto.getAuthor());
        bookRepo.save(book);
        return bookDto;
    }

    @Override
    public void deleteBookById(Integer id) {
        bookRepo.deleteById(id);
    }

    @Override
    public List<BookDto> findAllBooksWithStock() {
        List<Book> book = bookRepo.findAllBookWithStock();
        List<BookDto> bookDtos = bookDtoConverter.entityToDto(book);
        return bookDtos;
    }
}
