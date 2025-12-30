package com.RESTful.demo.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.ISBN;

public record BookCreateRequest(
        @NotBlank @Size(min = 1, max = 50) String title,
        @NotBlank @Size(min = 1, max = 30) String author,
        @ISBN @NotBlank String isbn
) {
}
