package com.abhiyan.bookrentalsystem.dto;
import com.abhiyan.bookrentalsystem.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    Integer noOfPages;

//    @NotBlank(message = "ISBN is required.")
    Long isbn;

    @Min(value=0,message = "Rating is required ")
    Double rating;

    @Min(value=0,message = "Stock Count is required. ")
    Integer stockCount;

    @NotBlank(message = "Date is required. ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    String publishedDate;

    String photo;

    List<Integer> authorId;

    Category category;

    Integer categoryId;

}
