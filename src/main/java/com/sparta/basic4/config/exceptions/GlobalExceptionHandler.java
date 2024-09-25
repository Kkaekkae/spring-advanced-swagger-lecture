package com.sparta.basic4.config.exceptions;

import com.sparta.basic4.application.exceptions.GlobalException;
import com.sparta.basic4.application.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalException> handleProductNotFoundException(NotFoundException ex) {
        GlobalException exception = new GlobalException(ex.getHttpStatus(), ex.getMessage());
        return new ResponseEntity<>(exception, exception.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<GlobalException> handleDBEntityNotFound(MethodArgumentNotValidException ex) {
        String message = ex.getFieldErrors().stream().findFirst()
                .map(error -> "%s: %s".formatted(error.getField(), error.getDefaultMessage()))
                .orElse("Unexpected reason");

        GlobalException exception = new GlobalException(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(exception, exception.getStatusCode());
    }
}
