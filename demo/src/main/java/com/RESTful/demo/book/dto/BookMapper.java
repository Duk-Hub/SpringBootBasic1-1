package com.RESTful.demo.book.dto;

import com.RESTful.demo.book.domain.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public static Book createBook(BookCreateRequest req) {
        return new Book(req.title(), req.author(), req.isbn());
    }

    public static BookResponse createBookResponse(Book b) {
        return new BookResponse(b.getId(),b.getTitle(), b.getAuthor(), b.getIsbn(),b.getCreatedAt());
    }

    public static BookSummaryResponse createBookSummaryResponse(Book b) {
        return new BookSummaryResponse(b.getId(),b.getTitle(),b.getAuthor());
    }
}
