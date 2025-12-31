package com.RESTful.demo.service;

import com.RESTful.demo.book.domain.Book;
import com.RESTful.demo.book.dto.BookCreateRequest;
import com.RESTful.demo.book.dto.BookResponse;
import com.RESTful.demo.book.repository.BookRepository;
import com.RESTful.demo.book.service.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void createBookTest(){
        //given
        BookCreateRequest request = new BookCreateRequest("제목1","저자1","9788995432105");
        Book book = new Book("제목1","저자1","9788995432105");
        given(bookRepository.save(any())).willReturn(book);

        //when
        BookResponse result = bookService.createBook(request);

        //then
        Assertions.assertThat(result.title()).isEqualTo(book.getTitle());
        verify(bookRepository, times(1)).save(any());

    }
}
