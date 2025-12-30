package com.RESTful.demo.global.exception;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException e, HttpServletRequest request) {
        ErrorCode code = ErrorCode.NOT_FOUND;
        log.warn("EX errorCode={} exceptionType={} path={}",code.name(),e.getClass().getSimpleName(),request.getRequestURI());

        ErrorResponse body = ErrorResponse.of(
                HttpStatus.NOT_FOUND.value(),
                code,
                "해당 도서를 찾을 수 없습니다.",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(DuplicateIsbnException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateIsbnException(DuplicateIsbnException e, HttpServletRequest request) {
        ErrorCode code = ErrorCode.CONFLICT;
        log.warn("EX errorCode={} exceptionType={} path={}",code.name(),e.getClass().getSimpleName(),request.getRequestURI());

        ErrorResponse body = ErrorResponse.of(
                HttpStatus.CONFLICT.value(),
                code,
                "이미 등록된 ISBN입니다.",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        ErrorCode code = ErrorCode.BAD_REQUEST;
        log.warn("EX errorCode={} exceptionType={} path={}",code.name(),e.getClass().getSimpleName(),request.getRequestURI());

        List<FieldViolation> violations = e.getBindingResult().getFieldErrors().stream().map(this::toViolation).toList();

        ErrorResponse body = ErrorResponse.of(
                HttpStatus.BAD_REQUEST.value(),
                code,
                "입력값이 올바르지 않습니다",
                request.getRequestURI(),
                violations
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        ErrorCode code = ErrorCode.INTERNAL_SERVER_ERROR;
        log.error("EX errorCode={} exceptionType={} path={}",code.name(),e.getClass().getSimpleName(),request.getRequestURI());

        ErrorResponse body = ErrorResponse.of(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                code,
                "내부 서버 오류",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        ErrorCode code = ErrorCode.REQUEST_BODY_INVALID;
        log.warn("EX errorCode={} exceptionType={} path={}",code.name(),e.getClass().getSimpleName(),request.getRequestURI());

        ErrorResponse body = ErrorResponse.of(
                HttpStatus.BAD_REQUEST.value(),
                code,
                "요청 본문의 형식이 올바르지 않습니다.",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    private FieldViolation toViolation(FieldError fieldError) {
        return new FieldViolation(
                fieldError.getField(),
                fieldError.getCode(),
                fieldError.getDefaultMessage()
        );
    }
}
