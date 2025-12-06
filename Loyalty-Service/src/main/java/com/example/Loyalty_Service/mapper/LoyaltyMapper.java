package com.example.Loyalty_Service.mapper;

import com.example.Loyalty_Service.dto.LoyaltyDTO;
import com.example.Loyalty_Service.model.Loyalty;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class LoyaltyMapper {

    // Entity -> DTO
    public LoyaltyDTO toDto(Loyalty loyalty) {
        if (loyalty == null) return null;

        String transactionDateStr = null;
        if (loyalty.getTransactionDate() != null) {
            transactionDateStr = loyalty.getTransactionDate().toLocalDateTime().toString();
        }

        return new LoyaltyDTO(
                loyalty.getId(),
                loyalty.getGuestId(),
                loyalty.getPoints(),
                loyalty.getType(),
                loyalty.getStatus(),
                loyalty.getDescription(),
                transactionDateStr
        );
    }

    // DTO -> Entity
    public Loyalty toEntity(LoyaltyDTO dto) {
        if (dto == null) return null;

        Timestamp transactionDate;
        if (dto.transactionDate() != null && !dto.transactionDate().trim().isEmpty()) {
            try {
                transactionDate = Timestamp.valueOf(LocalDateTime.parse(dto.transactionDate()));
            } catch (Exception e) {
                transactionDate = new Timestamp(System.currentTimeMillis());
            }
        } else {
            transactionDate = new Timestamp(System.currentTimeMillis());
        }

        return Loyalty.builder()
                .id(dto.id())
                .guestId(dto.guestId())
                .points(dto.points())
                .type(dto.type())
                .status(dto.status())
                .description(dto.description())
                .transactionDate(transactionDate)
                .build();
    }
}