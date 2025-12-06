package com.example.Loyalty_Service.exception;

import com.example.Loyalty_Service.dto.ErrorResponse;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExecptionHandle {

    @ExceptionHandler(ResourceNotFoundException.class)
    public @NonNull ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", "")) // You can set the request path here if needed
                .build();

        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(Exception.class)
    public @NonNull ResponseEntity<ErrorResponse> handleGeneralException(Exception ex, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", "")) // You can set the request path here if needed
                .build();

        return ResponseEntity.status(500).body(response);
    }
}
