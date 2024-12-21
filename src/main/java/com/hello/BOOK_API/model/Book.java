package com.hello.BOOK_API.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "books_tbl")
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;

    @Column(name = "book_name")
    private String name;

    @Column(name = "book_price")
    private int price;

    @Column(name = "book_author")
    private String author;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

}
