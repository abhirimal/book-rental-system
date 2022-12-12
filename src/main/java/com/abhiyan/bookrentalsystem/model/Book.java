package com.abhiyan.bookrentalsystem.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String name;

    Integer noOfPages;

    Long isbn;

    Double rating;

    Integer stockCount;

    LocalDate publishedDate;

    String photo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="author_book",
        joinColumns = {@JoinColumn(name = "book_id")},
        inverseJoinColumns = {@JoinColumn(name = "author_id")})

    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name = "category_id",
        referencedColumnName = "id")

    private Category category;

}
