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
        bookDto.setNoOfPages(book.getNoOfPages());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setRating(book.getRating());
        bookDto.setPublishedDate(String.valueOf(book.getPublishedDate()));
        bookDto.setFilePath(book.getFilePath());
        bookDto.setStockCount(book.getStockCount());
        bookDto.setCategoryId(book.getCategory().getId());
        bookDto.setCategory(book.getCategory());
        bookDto.setAuthors(book.getAuthors());
        return bookDto;
    }

    public Book dtoToEntity(BookDto bookDto){
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setNoOfPages(bookDto.getNoOfPages());
        book.setIsbn(bookDto.getIsbn());
        book.setRating(bookDto.getRating());
//        book.setPublishedDate(bookDto.getPublishedDate());
//        book.setPhoto(bookDto.getPhoto());
        book.setStockCount(bookDto.getStockCount());
//        book.getCategory().setId(bookDto.getCategoryId());
        return book;
    }

    public List<BookDto> entityToDto(List<Book> categories){
        return categories.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }
}
