package com.group.learn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}

