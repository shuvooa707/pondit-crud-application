package com.cruddb.ponditcruddb.controller;


import com.cruddb.ponditcruddb.model.Book;
import com.cruddb.ponditcruddb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping({"/", ""})
    public List<Book> index() {
        return bookService.findAll();
    }
    @GetMapping({"{id}/", "{id}"})
    public Optional<Book> show(@PathVariable(name = "id") Long id) {
        return bookService.findById(id);
    }
    @PostMapping({"store/", "store"})
    public ResponseEntity<?> store(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "author") String author,
        @RequestParam(name = "pages") Long pages,
        @RequestParam(name = "isbn") String isbn,
        @RequestParam(name = "publisher") String publisher,
        @RequestParam(name = "yearPublished") Integer yearPublished
    ) {
        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setPages(Math.toIntExact(pages));
        newBook.setIsbn(isbn);
        newBook.setPublisher(publisher);
        newBook.setYearPublished(yearPublished);
        bookService.save(newBook);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PostMapping({"destroy/{id}", "destroy/{id}"})
    public ResponseEntity<?> store(@PathVariable(name = "id") Long id) {
        bookService.destroy(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
