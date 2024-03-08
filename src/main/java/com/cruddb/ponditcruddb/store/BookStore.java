package com.cruddb.ponditcruddb.store;


import com.cruddb.ponditcruddb.model.Book;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
public class BookStore {
    private List<Book> books = new ArrayList<>();
    private Map<Long, Boolean> bookIds = new HashMap<>();

    BookStore(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book newBook) {
        books.add(newBook);
    }

    public Book findById(Long id) {
        return books
                 .stream()
                 .filter(book -> Objects.equals(book.getId(), id))
                 .findFirst()
                 .orElse(null);
    }

    public void removeById(Long id) {
        books = books
                  .stream()
                  .filter(b -> b.getId() != id)
                  .toList();
    }

    public Long getNewUniqueId() throws Throwable {
        Long id = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            if (!bookIds.containsKey(id)) {
                return id;
            }
        }
        throw new Throwable("ID Not Available");
    }
}
