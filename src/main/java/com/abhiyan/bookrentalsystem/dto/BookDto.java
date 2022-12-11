package com.abhiyan.bookrentalsystem.dto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class BookDto {

    Integer id;

    @NotBlank(message = "Book name is required. ")
    String name;

    @NotBlank(message = "ISBN is required.")
    Integer ISBN;

    @NotBlank(message = "Rating is required. ")
    Double rating;

    @NotBlank(message = "Stock Count is required.")
    Integer stockCount;

    @NotBlank(message = "Date is required. ")
    Date publishedDate;

    String photo;

}
