package com.example.booking_service.service;

import com.example.booking_service.entity.Booking;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {

    Booking create(Booking booking);
    Booking update(Booking booking, Long id);
    Booking getById(Long id);
    void delete(Long id);
}
