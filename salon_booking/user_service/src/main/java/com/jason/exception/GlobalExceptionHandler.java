package com.jason.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.jason.payloadResponse.ExceptionResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> ExceptionHandler(Exception e, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
            e.getMessage(),
            request.getDescription(false),LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
    
}
