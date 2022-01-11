package com.household.utils;

import com.household.exception.DuplicatedException;
import com.household.exception.NotFoundException;
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

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<ErrorResponse> badRequestHandler(RuntimeException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .errorMessage(e.getMessage())
            .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<ErrorResponse> notFoundHandler(RuntimeException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.NOT_FOUND.value())
            .errorMessage(e.getMessage())
            .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

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
