package com.RESTful.demo.book.service;

import com.RESTful.demo.book.dto.BookCreateRequest;
import com.RESTful.demo.book.dto.BookResponse;
import com.RESTful.demo.book.dto.BookSummaryResponse;
import com.RESTful.demo.book.dto.BookUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponse createBook(BookCreateRequest request);
    Page<BookSummaryResponse> getBooks(Pageable pageable);
    BookResponse getBookById(Long id);
    BookResponse updateBook(Long id, BookUpdateRequest request);
    void deleteBook(Long id);

}
