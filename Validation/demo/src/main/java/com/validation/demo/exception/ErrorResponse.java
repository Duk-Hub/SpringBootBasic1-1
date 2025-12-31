package com.validation.demo.exception;


import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        ErrorCode code,
        String message,
        String path,
        LocalDateTime timestamp,
        List<FieldViolation> violations
) {
    public static ErrorResponse of(int status, ErrorCode code, String message, String path) {
        return new ErrorResponse(status, code, message, path, LocalDateTime.now(), List.of());
    }

    public static ErrorResponse of(int status, ErrorCode code, String message,String path, List<FieldViolation> violations) {
        return new ErrorResponse(status, code, message, path, LocalDateTime.now(), violations);
    }
}
