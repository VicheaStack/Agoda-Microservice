package com.group.learn.service;

import com.group.learn.entity.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GuestQueryMethod {

    Guest getGuestById(Long id);

    Page<Guest> getAllGuests(Pageable pageable);

}
