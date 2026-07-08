package com.example.booking_service.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "http://GUEST-SERVICE")
public interface GuessFeginClient {



}
