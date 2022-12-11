package com.abhiyan.bookrentalsystem.converter;

import com.abhiyan.bookrentalsystem.dto.BookDto;
import com.abhiyan.bookrentalsystem.model.Book;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookDtoConverter {

    public BookDto entityToDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setISBN(book.getISBN());
        bookDto.setRating(book.getRating());
        bookDto.setPublishedDate(book.getPublishedDate());
        bookDto.setPhoto(book.getPhoto());
        bookDto.setStockCount(book.getStockCount());
        return bookDto;
    }

    public Book dtoToEntity(BookDto bookDto){
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setISBN(bookDto.getISBN());
        book.setRating(bookDto.getRating());
        book.setPublishedDate(bookDto.getPublishedDate());
        book.setPhoto(bookDto.getPhoto());
        book.setStockCount(bookDto.getStockCount());
        return book;
    }

    public List<BookDto> entityToDto(List<Book> categories){
        return categories.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }
}
