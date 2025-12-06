package com.group.learn.serviceImpl;

import com.group.learn.entity.Guest;
import com.group.learn.exception.ResourceNotFoundException;
import com.group.learn.repository.GuestRepository;
import com.group.learn.service.GuestQueryMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GuestQeuryServiceImpl implements GuestQueryMethod {

    private final GuestRepository guestRepository;

    public GuestQeuryServiceImpl(
                                 GuestRepository guestRepository
                                 ) {
        this.guestRepository = guestRepository;
    }

    @Cacheable(value = "guest", key = "#id")
    @Override
    public Guest getGuestById(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found by id {} " + id));
        log.info("Guest not found by id");
        return guest;
    }

    @Cacheable(value = "guest", key = "#pageable")
    @Override
    public Page<Guest> getAllGuests(Pageable pageable) {
        Page<Guest> all = guestRepository.findAll(pageable);

        if(all.getContent().isEmpty()){
            throw new ResourceNotFoundException("Guest not found {} ");
        }

        log.info("get all Guests for LoyaltyTransaction, find={}" , all);
        return all;
    }

}
