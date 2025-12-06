package com.example.booking_service.serviceImpl;

import com.example.booking_service.entity.Booking;
import com.example.booking_service.entity.Reviews;
import com.example.booking_service.execption.ResourceNotFoundException;
import com.example.booking_service.repository.BookingRepository;
import com.example.booking_service.repository.ReviewsRepository;
import com.example.booking_service.service.ReviewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository repository;
    private final BookingRepository bookingRepository;

    public ReviewsServiceImpl(ReviewsRepository repository,
                              BookingRepository bookingRepository) {
        this.repository = repository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Reviews create(Reviews review) {
        if(review == null){
            throw new IllegalArgumentException("Review cannot be null");
        }

        if(review.getBookingId() == null){
            throw new IllegalArgumentException("Booking ID cannot be null");
        }

        if(review.getRating() == null){
            throw new IllegalArgumentException("Rating cannot be null");
        }

        boolean checking = bookingRepository.existsById(review.getBookingId());
        if(!checking){
            throw new ResourceNotFoundException("Booking not found with id: " + review.getBookingId());
        }

        Reviews save = repository.save(review);
        log.info("Review created with id: {}", save.getId());
        return save;
    }

    @Override
    public Reviews update(Reviews review, Long id) {
        Reviews reviews = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));

        reviews.setRating(review.getRating());
        reviews.setComments(review.getComments());

        repository.save(reviews);
        log.info("Review updated with id: {}", id);

        return reviews;
    }

    @Override
    public Reviews getById(Long id) {
        Reviews reviews = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
        log.info("Review retrieved with id: {}", id);
        return reviews;
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id)){
            repository.deleteById(id);
            log.info("Review deleted with id: {}", id);
        } else {
            throw new ResourceNotFoundException("Review not found with id: " + id);
        }
    }
}
