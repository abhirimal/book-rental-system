package com.abhiyan.bookrentalsystem.model;

import com.abhiyan.bookrentalsystem.enums.RentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Long code;

    private LocalDate fromDate;

    private LocalDate toDate;

    @Enumerated(value = EnumType.STRING)
    private RentType rentType;

    private Integer noOfDays;

    @ManyToOne
    @JoinColumn(name = "book_id",
        referencedColumnName = "id")
    private Book book;

    @ManyToOne
    @JoinColumn(name="member_id",
        referencedColumnName = "id")
    private Member member;

}
