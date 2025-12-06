package com.example.Loyalty_Service.service;

import com.example.Loyalty_Service.model.Loyalty;
import org.springframework.stereotype.Service;

@Service
public interface LoyaltyService {

    Loyalty create(Loyalty loyalty);
    Loyalty getById(Long id);
    Loyalty update(Long id, Loyalty loyalty);
    void delete(Long id);
}
