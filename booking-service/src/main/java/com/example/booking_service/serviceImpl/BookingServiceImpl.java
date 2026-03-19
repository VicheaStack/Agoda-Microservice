package com.example.booking_service.serviceImpl;

import com.example.booking_service.Enum.BookingStatus;
import com.example.booking_service.dto.RoomBookingSnapshotDTO;
import com.example.booking_service.entity.Booking;
import com.example.booking_service.execption.ResourceNotFoundException;
import com.example.booking_service.repository.BookingRepository;
import com.example.booking_service.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;
    private final RoomServiceReactive roomServiceReactive;

    public BookingServiceImpl(BookingRepository repository,
                              RoomServiceReactive roomServiceReactive) {
        this.repository = repository;
        this.roomServiceReactive = roomServiceReactive;
    }

    @Override
    public Booking create(Booking booking) {
        log.info("Start booking process for room {} :" , booking.getId());

        Page<RoomBookingSnapshotDTO> block = roomServiceReactive
                .checkRoomAvailability(0, 10)
                .block(Duration.ofSeconds(10));

        assert block != null;
        boolean available = block.stream()
                .anyMatch(room -> room.getRoomId().equals(booking.getRoomId()));

        if(!available) {
            log.error("Room {} is not available for booking", booking.getRoomId());
            throw new RuntimeException("Selected room not available");
        }

        booking.setStatus(BookingStatus.PENDING);
        Booking savedBooking = repository.save(booking);

        log.info("Booking save successfully with reference {} ", savedBooking.getBookingReference());
        return savedBooking;
    }

    @Override
    public Booking update(Booking booking, Long id) {
        Booking existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        // Allowed to update
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
