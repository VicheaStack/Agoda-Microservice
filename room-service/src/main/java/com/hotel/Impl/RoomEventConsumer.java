package com.hotel.Impl;

import com.hotel.entity.event.BookingCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RoomEventConsumer {

    @KafkaListener(topics = "booking-created-topic")
    public void handleBooking(BookingCreatedEvent event){
        System.out.println("Room reserve " + event.getBookingId());

    }
}
