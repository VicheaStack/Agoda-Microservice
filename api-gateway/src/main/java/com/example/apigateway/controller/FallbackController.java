package com.example.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    // Change this to be very specific for your YAML's "forward:/fallback/booking"
    @GetMapping("/booking")
    public ResponseEntity<String> bookingFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Booking service is temporarily unavailable. Please try later.");
    }

    // You can keep the generic one as a backup
    @GetMapping("/{service}")
    public ResponseEntity<String> serviceFallback(@PathVariable String service) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(String.format("%s service is temporarily unavailable.", service));
    }
}