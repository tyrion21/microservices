package com.jason.payloadResponse;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ExceptionResponse {
    private String message;
    private String error;
    private LocalDateTime timestamp;
}
