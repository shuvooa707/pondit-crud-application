package com.cruddb.ponditcruddb.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "title")
    private String title;


    @Column(name = "author")
    private String author;

    @Column(name = "pages")
    private int pages;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "yearPublished")
    private int yearPublished;


}
