package com.abhiyan.bookrentalsystem.dto;
import com.abhiyan.bookrentalsystem.model.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BookDto {

    Integer id;

//    @NotBlank(message = "Book name is required. ")
    String name;

//    @NotBlank(message = "Total number of pages is required.")
//    @Min(value=0,message = "Pages cannot be negative. ")
    Integer noOfPages;

//    @NotBlank(message = "ISBN is required.")
    Integer ISBN;

//    @NotBlank(message = "Rating is required. ")
    Double rating;

//    @NotBlank(message = "Stock Count is required.")
    Integer stockCount;

//    @NotBlank(message = "Date is required. ")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date publishedDate;

    String photo;

    List<AuthorDto> author;

    Category category;

    Integer categoryId;

}
