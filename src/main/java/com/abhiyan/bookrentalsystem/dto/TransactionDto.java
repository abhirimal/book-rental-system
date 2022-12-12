package com.abhiyan.bookrentalsystem.dto;
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

    private Long code;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Integer noOfDays;

    private String rentStatus;
}
