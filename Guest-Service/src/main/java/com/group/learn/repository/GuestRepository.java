package com.group.learn.repository;

import com.group.learn.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> id(Long id);
    Optional<Guest> findByPhone(String phone);
}
