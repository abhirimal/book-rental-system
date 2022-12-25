package com.abhiyan.bookrentalsystem.model;
import com.abhiyan.bookrentalsystem.enums.AccountState;
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

@Table(name = "tbl_book",uniqueConstraints = {
        @UniqueConstraint(name = "isbn",columnNames = {"isbn"})
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "no_of_pages")
    Integer noOfPages;

    @Column(name = "isbn")
    Long isbn;

    @Column(name = "rating")
    Double rating;

    @Column(name = "stock_count")
    Integer stockCount;

    @Column(name="published_date")
    LocalDate publishedDate;

    @Column(name = "file_path")
    String filePath;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_state")
    private AccountState accountState;

    @ManyToMany
    @JoinTable(name="author_book",
        joinColumns = {@JoinColumn(name = "book_id")},
        inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name = "category_id",
        referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;


}
