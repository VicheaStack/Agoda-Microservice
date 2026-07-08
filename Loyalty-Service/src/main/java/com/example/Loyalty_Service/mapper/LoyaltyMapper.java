package com.example.Loyalty_Service.mapper;

import com.example.Loyalty_Service.dto.LoyaltyDTO;
import com.example.Loyalty_Service.dto.LoyaltyRequestDTO;
import com.example.Loyalty_Service.model.Loyalty;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class LoyaltyMapper {

    private LoyaltyMapper() {
        // Prevent instantiation
    }

    public LoyaltyDTO toDTO(Loyalty loyalty) {
        if (loyalty == null) {
            return null;
        }

        LocalDateTime transactionDateTime = Optional.ofNullable(loyalty.getTransactionDate())
                .map(Timestamp::toLocalDateTime)
                .orElse(null);

        return new LoyaltyDTO(
                loyalty.getId(),
                loyalty.getGuestId(),
                loyalty.getPoints(),
                loyalty.getType(),
                loyalty.getStatus(),
                loyalty.getDescription(),
                transactionDateTime
        );
    }

    public Loyalty toEntity(LoyaltyRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        Loyalty loyalty = new Loyalty();
        loyalty.setGuestId(requestDTO.guestId());
        loyalty.setPoints(requestDTO.points());
        loyalty.setType(requestDTO.type());
        loyalty.setStatus(requestDTO.status());
        loyalty.setDescription(requestDTO.description());

        Optional.ofNullable(requestDTO.transactionDate())
                .map(Timestamp::valueOf)
                .ifPresent(loyalty::setTransactionDate);

        return loyalty;
    }
}