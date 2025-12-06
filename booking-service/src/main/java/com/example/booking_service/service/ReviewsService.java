package com.example.booking_service.service;

import com.example.booking_service.entity.Reviews;
import org.springframework.stereotype.Service;

@Service
public interface ReviewsService {

    Reviews create(Reviews review);
    Reviews update(Reviews review, Long id);
    Reviews getById(Long id);
    void delete(Long id);
}
