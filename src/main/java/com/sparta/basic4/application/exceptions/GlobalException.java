package com.sparta.basic4.application.exceptions;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class GlobalException{
    HttpStatus statusCode;
    String message;
    int code;

    public GlobalException(HttpStatus statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
        this.code = statusCode.value();
    }
}
