package com.example.booking_service.serviceImpl;

import com.example.booking_service.client.LoyaltyFeginClient;
import com.example.booking_service.dto.LoyaltyRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class LoyaltyService {

    private final LoyaltyFeginClient loyaltyFeginClient;

    public LoyaltyService(LoyaltyFeginClient loyaltyFeginClient) {
        this.loyaltyFeginClient = loyaltyFeginClient;
    }

    public LoyaltyRequestDTO findById(Long id) {
        return loyaltyFeginClient.findById(id);
    }

    public LoyaltyRequestDTO update(Long id, LoyaltyRequestDTO loyaltyRequestDTO){
        return loyaltyFeginClient.updateById(id, loyaltyRequestDTO);
    }

    public LoyaltyRequestDTO deleteById(Long id){
        return loyaltyFeginClient.deleteById(id);
    }
}
