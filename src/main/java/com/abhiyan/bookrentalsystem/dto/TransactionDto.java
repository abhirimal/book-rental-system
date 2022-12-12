package com.abhiyan.bookrentalsystem.dto;
import com.abhiyan.bookrentalsystem.enums.RentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Integer id;

    private String code;

    private Integer noOfDays;

    private Integer memberId;

    private Integer bookId;
}
