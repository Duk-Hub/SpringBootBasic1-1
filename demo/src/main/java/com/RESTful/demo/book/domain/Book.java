package com.RESTful.demo.book.domain;

import com.RESTful.demo.book.dto.BookUpdateRequest;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 30, nullable = false)
    private String author;

    @Column(nullable = false,unique = true)
    private String isbn;

    @Column(nullable = false)
    private LocalDate createdAt;

    protected  Book() {
    }

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.createdAt = LocalDate.now();
    }

    public void updateBook(BookUpdateRequest request){
        boolean hasTitle = request.title() != null && !request.title().isBlank();
        boolean hasAuthor = request.author() != null && !request.author().isBlank();
        if (hasTitle){
            this.title = request.title();
        }
        if (hasAuthor){
            this.author = request.author();
        }
    }
}
