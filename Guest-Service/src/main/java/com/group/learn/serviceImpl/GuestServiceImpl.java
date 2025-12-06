package com.group.learn.serviceImpl;

import com.group.learn.entity.Guest;
import com.group.learn.exception.ResourceNotFoundException;
import com.group.learn.repository.GuestRepository;
import com.group.learn.service.GuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Slf4j
@Service
public class GuestServiceImpl implements GuestService {
    
    private final GuestRepository guestRepository;
    
    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @CachePut(value = "Guest", key = "#result.id")
    @Override
    public Guest create(Guest guest) {
        guest.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        guest.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        Guest saved = guestRepository.save(guest);
        log.info("✅ Guest created successfully: id={}, email={}", saved.getId(), saved.getEmail());
        return saved;
    }

    @CachePut(value = "Guest", key = "#id")
    @Transactional
    @Override
    public Guest update(Long id, Guest guest) {
        Guest updatedGuest = guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found with id: " + id));

        updatedGuest.setFirstName(guest.getFirstName());
        updatedGuest.setLastName(guest.getLastName());
        updatedGuest.setDateOfBirth(guest.getDateOfBirth());
        updatedGuest.setPhone(guest.getPhone());
        updatedGuest.setEmail(guest.getEmail());
        updatedGuest.setPassportNumber(guest.getPassportNumber());
        updatedGuest.setNationality(guest.getNationality());
        updatedGuest.setGender(guest.getGender());
        updatedGuest.setAddress(guest.getAddress());
        Guest save = guestRepository.save(updatedGuest);
        log.info("Guest updated successfully: id={}", save);
        return save;
    }

    @CacheEvict(value = "Guest", key = "#id")
    @Transactional
    @Override
    public void delete(Long id) {
        if (!guestRepository.existsById(id)){
            throw new ResourceNotFoundException("Guest not found with id: " + id);
        } else {
            guestRepository.deleteById(id);
            log.info("Guest deleted with id: {}", id);
        }
    }
}
