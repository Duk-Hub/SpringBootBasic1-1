package com.RESTful.demo.book.dto;

public record BookSummaryResponse(
        Long id,
        String title,
        String author
) {
}
