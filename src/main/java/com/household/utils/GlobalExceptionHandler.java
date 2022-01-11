package com.household.utils;

import com.household.exception.DuplicatedException;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DuplicatedException.class})
    protected ResponseEntity<ErrorResponse> conflictHandler(RuntimeException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.CONFLICT.value())
            .errorMessage(e.getMessage())
            .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @Value
    @Builder
    public static class ErrorResponse {

        LocalDateTime timestamp;
        Integer status;
        String errorMessage;
    }
}
