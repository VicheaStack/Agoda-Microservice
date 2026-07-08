package com.example.booking_service.client;

import com.example.booking_service.dto.GuestRequestDTO;
import com.example.booking_service.dto.LoyaltyRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "LOYALTY-SERVICE")
public interface LoyaltyFeginClient {

    @PostMapping("/api/v1/loyalty/{id}")
    LoyaltyRequestDTO findById(@PathVariable Long id);

    @PutMapping("/api/v1/loyalty/{id}")
    LoyaltyRequestDTO updateById(@PathVariable Long id, @RequestBody LoyaltyRequestDTO loyaltyRequestDTO);

    @DeleteMapping("/api/v1/loyalty/{id}")
    LoyaltyRequestDTO deleteById(@PathVariable Long id);

}
