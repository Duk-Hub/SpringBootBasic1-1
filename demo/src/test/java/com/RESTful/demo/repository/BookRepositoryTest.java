package com.RESTful.demo.repository;

import com.RESTful.demo.book.domain.Book;
import com.RESTful.demo.book.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void findBookByIdTest() {
        //given
        Book book = new Book("제목1","저자1","9788995432105");
        bookRepository.save(book);

        //when
        Optional<Book> found = bookRepository.findById(1L);

        //then
        assertThat(found.isPresent());
        assertThat(found.get().getTitle()).isEqualTo("제목1");
    }
}
