package com.example.booking_service.serviceImpl;

import com.example.booking_service.entity.Booking;
import com.example.booking_service.entity.event.BookingCreateEvent;
import com.example.booking_service.repository.BookingRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceEvent {

    private final BookingRepository bookingRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public BookingServiceEvent(BookingRepository bookingRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.bookingRepository = bookingRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Booking createBookingEvent(Booking booking){
        Booking save = bookingRepository.save(booking);

        BookingCreateEvent event = new BookingCreateEvent(
          save.getId(),
                save.getRoomId(),
                save.getGuestEmail()
        );

        kafkaTemplate.send("Booking-create-topic", kafkaTemplate);
        return save;
    }
}
