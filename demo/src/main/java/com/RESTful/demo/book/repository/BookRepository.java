package com.RESTful.demo.book.repository;

import com.RESTful.demo.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    boolean existsByIsbn(String isbn);
}
