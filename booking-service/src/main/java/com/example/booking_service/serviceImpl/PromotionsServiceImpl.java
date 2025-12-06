package com.example.booking_service.serviceImpl;

import com.example.booking_service.entity.Booking;
import com.example.booking_service.entity.Promotions;
import com.example.booking_service.execption.ResourceNotFoundException;
import com.example.booking_service.repository.BookingRepository;
import com.example.booking_service.repository.PromotionsRepository;
import com.example.booking_service.service.PromotionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PromotionsServiceImpl implements PromotionsService {

    private final PromotionsRepository repository;
    private final BookingRepository bookingRepository;

    public PromotionsServiceImpl(PromotionsRepository repository,
                                 BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        this.repository = repository;
    }

    @Override
    public Promotions create(Promotions promotion) {

        if(promotion == null){
            throw new IllegalArgumentException(" Promotion cannot be null");
        }

        if(promotion.getCode() == null || promotion.getCode().isEmpty()){
            throw new IllegalArgumentException(" Promotion code cannot be null or empty");
        }

        if(promotion.getDescription() == null || promotion.getDescription().isEmpty()){
            throw new IllegalArgumentException(" Promotion description cannot be null or empty");
        }

        if (promotion.getBookId() == null) {
            throw new IllegalArgumentException(" Booking ID cannot be null");
        }

        boolean bookingExist = bookingRepository.existsById(promotion.getBookId());
        if(!bookingExist){
            throw new ResourceNotFoundException("Booking not found with id: " + promotion.getBookId());
        }

        Promotions save = repository.save(promotion);
        log.info("New promotion created: {}", save);
        return save;
    }

    @Override
    public Promotions update(Promotions promotion, Long id) {
        Promotions promotions = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(" Promotion not found with id: " + id));

        promotions.setCode(promotion.getCode());
        promotions.setDescription(promotion.getDescription());
        promotions.setActive(promotion.getActive());
        promotions.setActive(false);

        repository.save(promotions);
        log.info("Promotion updated: {}", promotions);

        return promotions;
    }

    @Override
    public Promotions getById(Long id) {
        Promotions promotions = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(" Promotion not found with id: " + id));

        log.info("Promotion retrieved: {}", promotions);
        return promotions;
    }

    @Override
    public void delete(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            log.info("Promotion deleted with id: {}", id);
        } else {
            log.warn("Attempted to delete non-existing promotion with id: {}", id);
            throw new ResourceNotFoundException(" Promotion not found with id: " + id);
        }
    }
}
