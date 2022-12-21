package com.abhiyan.bookrentalsystem.dto;
import com.abhiyan.bookrentalsystem.model.Author;
import com.abhiyan.bookrentalsystem.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    Integer id;

    @NotBlank(message = "Book name is required. ")
    String name;

//    @NotBlank(message = "Total number of pages is required.") - cannot use this for integer type
    @Min(value=0,message = "Pages cannot be negative. ")
    @NotNull(message = " Number of pages is required")
    Integer noOfPages;

    @NotNull(message = "ISBN is required")
    @Range(min = 100000000,max = 999999999,message = "ISBN number should be of 9 digits" )
//    @Min(value = 1000000000,message = "this is min check")
//    @Max(value = 999999999,message = "this is max check")
    Long isbn;

    @Min(value=0,message = "Pages cannot be negative. ")
    @NotNull(message = "Rating is required")
    Double rating;

    @Min(value=0,message = "Stock Count cannot be negative. ")
    @NotNull(message = "Stock is required")
    Integer stockCount;

    @NotBlank(message = "Date is required. ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    String publishedDate;

    String photo;

    @NotEmpty(message = "Select atleast one author")
    List<Integer> authorId;

    Category category;

    @NotNull(message = "Select atleast one category")
    Integer categoryId;


    List<Author> authors;

}
