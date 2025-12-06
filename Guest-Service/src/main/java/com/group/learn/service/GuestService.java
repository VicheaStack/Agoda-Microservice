package com.group.learn.service;

import com.group.learn.entity.Guest;
import org.springframework.stereotype.Service;

@Service
public interface GuestService {
    Guest create(Guest guest);
    Guest update(Long id, Guest guest);
    void delete(Long id);
}
