package com.RESTful.demo.global.exception;

import org.springframework.validation.FieldError;

public record FieldViolation(
        String field,
        String code,
        String message
) {
    public static FieldViolation from(FieldError fieldError) {
        return new FieldViolation(fieldError.getField(), fieldError.getCode(), fieldError.getDefaultMessage());
    }
}
