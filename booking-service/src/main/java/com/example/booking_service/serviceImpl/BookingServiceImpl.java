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
    private final RoomServiceReactive roomServiceReactive;

    public BookingServiceImpl(BookingRepository repository,
                              RoomServiceReactive roomServiceReactive) {
        this.repository = repository;
        this.roomServiceReactive = roomServiceReactive;
    }

    @Override
    public Mono<Booking> create(Booking booking) {
        log.info("Start booking process for room: {}", booking.getRoomId());

        return roomServiceReactive.checkRoomAvailability(0, 100)
                .collectList() // convert Flux<RoomBookingSnapshotDTO> -> Mono<List<…>>
                .flatMap(rooms -> {
                    if (rooms.isEmpty()) {
                        log.error("No available rooms returned from Room Service");
                        return Mono.error(new RuntimeException("Room Service returned no data"));
                    }

                    boolean available = rooms.stream()
                            .anyMatch(room -> Objects.equals(room.getRoomId(), booking.getRoomId()));

                    if (!available) {
                        log.error("Room {} is not available for booking", booking.getRoomId());
                        return Mono.error(new RuntimeException("Selected room not available"));
                    }

                    booking.setStatus(BookingStatus.PENDING);
                    if (booking.getBookingReference() == null) {
                        booking.setBookingReference("BK-" + UUID.randomUUID().toString().substring(0, 8));
                    }

                    return Mono.fromCallable(() -> repository.save(booking))
                            .subscribeOn(Schedulers.boundedElastic());
                })
                .timeout(Duration.ofSeconds(5))
                .doOnError(error -> log.error("Booking failed: {}", error.getMessage()));
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
}