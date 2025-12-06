package com.example.Loyalty_Service.repository;

import com.example.Loyalty_Service.model.Loyalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyRepository extends JpaRepository<Loyalty, Long> {

}

