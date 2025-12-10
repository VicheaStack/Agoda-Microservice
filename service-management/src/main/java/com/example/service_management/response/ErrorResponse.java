package com.example.service_management.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private LocalDateTime timeStamp;
    private int error;
    private int status;
    private String path;
}
