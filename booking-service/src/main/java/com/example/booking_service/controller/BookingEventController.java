package com.example.booking_service.controller;

import com.example.booking_service.dto.BookingEvent;
import com.example.booking_service.entity.Booking;
import com.example.booking_service.mapper.BookingEventMapper;
import com.example.booking_service.serviceImpl.BookingServiceEvent;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookingsEvent")
@RequiredArgsConstructor
public class BookingEventController {

    private final BookingServiceEvent serviceEvent;
    private final BookingEventMapper eventMapper;

    @PostMapping
    public BookingEvent event(@RequestBody BookingEvent event){
        Booking entity = eventMapper.entity(event);
        Booking bookingEvent = serviceEvent.createBookingEvent(entity);
        BookingEvent dto = eventMapper.dto(bookingEvent);
        return dto;
    }
}
