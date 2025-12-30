package com.RESTful.demo.global.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        ErrorCode errorCode,
        String message,
        LocalDateTime timestamp,
        String path,
        List<FieldViolation> fieldViolations
) {
    public static ErrorResponse of(int status,ErrorCode code, String message, String path, List<FieldViolation> fieldViolations) {
        return new ErrorResponse(status,code,message,LocalDateTime.now(),path,fieldViolations);
    }

    public static ErrorResponse of(int status,ErrorCode code, String message, String path) {
        return new ErrorResponse(status,code,message,LocalDateTime.now(),path,null);
    }
}
