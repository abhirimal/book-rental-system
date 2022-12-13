package com.abhiyan.bookrentalsystem.dto;
import com.abhiyan.bookrentalsystem.enums.RentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Integer id;

    private String code;

    @Min(value=0,message = "Days cannot be negative. ")
    @NotNull(message = " Number of days is required")
    private Integer noOfDays;

    @NotNull(message = "Select atleast one member")
    private Integer memberId;

    @NotNull(message = "Select atleast one book")
    private Integer bookId;




}
