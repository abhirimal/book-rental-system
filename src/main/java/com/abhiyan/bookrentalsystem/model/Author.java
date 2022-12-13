package com.abhiyan.bookrentalsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private String mobileNumber;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> bookList;
}
