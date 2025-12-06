package com.example.booking_service.serviceImpl;

import com.example.booking_service.entity.Booking;
import com.example.booking_service.execption.ResourceNotFoundException;
import com.example.booking_service.repository.BookingRepository;
import com.example.booking_service.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;

    public BookingServiceImpl(BookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Booking create(Booking booking) {
        Booking save = repository.save(booking);
        return save;
    }

    @Override
    public Booking update(Booking booking, Long id) {
        Booking existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        // Allowed to update
        existing.setGuestName(booking.getGuestName());
        existing.setGuestEmail(booking.getGuestEmail());
        existing.setCheckInDate(booking.getCheckInDate());
        existing.setCheckOutDate(booking.getCheckOutDate());
        existing.setNumGuests(booking.getNumGuests());
        existing.setSpecialRequests(booking.getSpecialRequests());
        existing.setStatus(booking.getStatus());
        existing.setCancellationReason(booking.getCancellationReason());

        return repository.save(existing);
    }


    @Override
    public Booking getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public void delete(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Booking not found");
        }
    }
}
