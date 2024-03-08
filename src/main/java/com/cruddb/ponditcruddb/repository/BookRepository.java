package com.cruddb.ponditcruddb.repository;

import com.cruddb.ponditcruddb.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
