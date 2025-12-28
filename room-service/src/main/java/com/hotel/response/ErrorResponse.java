package com.hotel.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timeStamp;
    private String error;
    private int status;
    private String message;
    private String path;
}
