package com.validation.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ErrorCode code = ErrorCode.VALIDATION_ERROR;

        List<FieldViolation> violations = ex.getBindingResult().getFieldErrors().stream().map(this::toViolation).toList();

        ErrorResponse body = ErrorResponse.of(
                HttpStatus.BAD_REQUEST.value(),
                code,
                "입력값이 올바르지 않습니다",
                request.getRequestURI(),
                violations
        );

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        ErrorCode code = ErrorCode.CONSTRAINT_VIOLATION;

        List<FieldViolation> violations = ex.getConstraintViolations().stream().map(this::toViolation).toList();

        ErrorResponse body = ErrorResponse.of(
                HttpStatus.BAD_REQUEST.value(),
                code,
                "쿼리 파라미터값이 올바르지 않습니다.",
                request.getRequestURI(),
                violations
        );

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorCode code = ErrorCode.NOT_FOUND;

        ErrorResponse body = ErrorResponse.of(
                HttpStatus.NOT_FOUND.value(),
                code,
                "해당 글이 없습니다",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }



    private FieldViolation toViolation(FieldError fieldError) {
        return FieldViolation.of(
                fieldError.getField(),
                fieldError.getDefaultMessage()
        );
    }

    private FieldViolation toViolation(ConstraintViolation<?> cv) {
        String field =  cv.getPropertyPath().toString();
        String message = cv.getMessage();

        return FieldViolation.of(field, message);
    }
}
