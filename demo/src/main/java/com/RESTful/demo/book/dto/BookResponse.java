package com.RESTful.demo.book.dto;

import java.time.LocalDate;

public record BookResponse(
        Long id,
        String title,
        String author,
        String isbn,
        LocalDate createdAt
) {
}
