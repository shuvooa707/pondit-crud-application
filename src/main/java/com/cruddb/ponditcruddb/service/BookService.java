package com.cruddb.ponditcruddb.service;

import com.cruddb.ponditcruddb.model.Book;
import com.cruddb.ponditcruddb.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void save(Book newBook) {
        bookRepository.save(newBook);
    }

    public void destroy(Long id) {
        bookRepository.deleteAllById(Collections.singleton(id));
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
}
