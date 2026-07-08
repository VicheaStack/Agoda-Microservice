package com.example.booking_service.serviceImpl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import com.example.booking_service.Enum.BookingStatus;
import com.example.booking_service.entity.Booking;
import com.example.booking_service.execption.ResourceNotFoundException;
import com.example.booking_service.repository.BookingRepository;
import com.example.booking_service.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;
    private final RoomService roomService;

    public BookingServiceImpl(BookingRepository repository,
                              RoomService roomService) {
        this.repository = repository;
        this.roomService = roomService;
    }

    @Override
    public Booking create(Booking booking) {
       log.info("Start booking process for room: {}", booking.getRoomId());

        Boolean roomAvailable = roomService.isRoomAvailable(booking.getRoomId());

        if(!roomAvailable) {
            log.warn("Room {} is not available", booking.getRoomId());
            throw new IllegalStateException("Selected room not available");
        }

        Booking bookingToSave = buildBooking(booking);

        return repository.save(bookingToSave);
    }

    @CacheEvict(value = "bookings", key = "#id")
    @Transactional
    @Override
    public Booking update(Booking booking, Long id) {
        Booking existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        existing.setGuestName(booking.getGuestName());
        existing.setGuestEmail(booking.getGuestEmail());
        existing.setActualCheckIn(booking.getActualCheckIn());
        existing.setActualCheckOut(booking.getActualCheckOut());
        existing.setRoomType(booking.getRoomType());
        existing.setRoomNumber(booking.getRoomNumber());
        existing.setNumberOfGuests(booking.getNumberOfGuests());
        existing.setSpecialRequests(booking.getSpecialRequests());
        existing.setStatus(booking.getStatus());
        existing.setCancellationReason(booking.getCancellationReason());

        return repository.save(existing);
    }

    @Cacheable(value = "bookings", key = "#id")
    @Transactional(readOnly = true)
    @Override
    public Booking getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @CacheEvict(value = "bookings", key = "#id")
    @Transactional
    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Booking not found");
        }
    }

    private String generateBookingReference() {
        String raw = UUID.randomUUID().toString().replace("-", "");
        return "BOOK-" + raw.substring(0, 12);
    }

    private Booking buildBooking(Booking booking) {
        return Booking.builder()
                .guestId(booking.getGuestId())
                .guestName(booking.getGuestName())
                .guestEmail(booking.getGuestEmail())
                .roomId(booking.getRoomId())
                .roomNumber(booking.getRoomNumber())
                .roomType(booking.getRoomType())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .numberOfGuests(booking.getNumberOfGuests())
                .specialRequests(booking.getSpecialRequests())
                .totalAmount(booking.getTotalAmount())
                .discountAmount(booking.getDiscountAmount())
                .taxAmount(booking.getTaxAmount())
                .status(BookingStatus.PENDING)
                .bookingReference(generateBookingReference())
                .build();
    }
}