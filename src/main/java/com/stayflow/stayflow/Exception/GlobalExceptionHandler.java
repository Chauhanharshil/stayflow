package com.stayflow.stayflow.Exception;

import com.stayflow.stayflow.dtos.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseError> handleResourceNotFoundException(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseError(e.getMessage(), LocalDateTime.now())
        );
    }
}
