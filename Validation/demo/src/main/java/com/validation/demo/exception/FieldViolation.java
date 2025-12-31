package com.validation.demo.exception;


public record FieldViolation(
        String field,
        String reason
) {
    public static FieldViolation of(String field, String reason) {
        return new FieldViolation(field, reason);
    }
}
