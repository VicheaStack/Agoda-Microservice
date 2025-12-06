package com.example.booking_service.service;

import com.example.booking_service.entity.Promotions;
import org.springframework.stereotype.Service;

@Service
public interface PromotionsService {

    Promotions create(Promotions promotion);
    Promotions update(Promotions promotion, Long id);
    Promotions getById(Long id);
    void delete(Long id);

}
