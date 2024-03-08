package com.cruddb.ponditcruddb.controller;


import com.cruddb.ponditcruddb.model.Book;
import com.cruddb.ponditcruddb.response.book.BookFoundResponse;
import com.cruddb.ponditcruddb.response.book.ResponseEnum;
import com.cruddb.ponditcruddb.service.BookService;
import com.cruddb.ponditcruddb.store.BookStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookStore bookStore;

    @GetMapping({"/", ""})
    public List<Book> index() {
        return bookStore.getBooks();
    }
    @GetMapping({"{id}/", "{id}"})
    public ResponseEntity<BookFoundResponse> show(@PathVariable(name = "id") Long id) {
        System.out.println(id);
        Book book = bookStore.findById(id);
        return new ResponseEntity<>(new BookFoundResponse(book, ResponseEnum.SUCCESS), HttpStatus.OK);
    }
    @PostMapping({"store/", "store"})
    public ResponseEntity<?> store(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "author") String author,
        @RequestParam(name = "pages") Long pages,
        @RequestParam(name = "isbn") String isbn,
        @RequestParam(name = "publisher") String publisher,
        @RequestParam(name = "yearPublished") Integer yearPublished
    ) throws Throwable {
        Book newBook = new Book();
        try{
            newBook.setId(bookStore.getNewUniqueId());
        } catch (Throwable throwable) {
            return new ResponseEntity<>("Failed", HttpStatus.EXPECTATION_FAILED);
        }
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setPages(Math.toIntExact(pages));
        newBook.setIsbn(isbn);
        newBook.setPublisher(publisher);
        newBook.setYearPublished(yearPublished);
        bookStore.addBook(newBook);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PostMapping({"destroy/{id}", "destroy/{id}"})
    public ResponseEntity<?> store(@PathVariable(name = "id") Long id) {
        bookStore.removeById(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
