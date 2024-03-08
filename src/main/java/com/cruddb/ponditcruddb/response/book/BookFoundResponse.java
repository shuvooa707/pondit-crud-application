package com.cruddb.ponditcruddb.response.book;

import com.cruddb.ponditcruddb.model.Book;

public class BookFoundResponse {
    public ResponseEnum message;
    public Book book;
    public BookFoundResponse(Book book, ResponseEnum message) {
        this.message = message;
        this.book = book;
    }
}
