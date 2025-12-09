package com.example.service_management.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingServiceResponseDTO {

    private Long id;
    private Long bookingId;
    private String bookingReference;
    private Long serviceId;
    private String serviceName;
    private Long staffId;
    private String staffName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private LocalDate serviceDate;
    private String notes;
    private LocalDateTime createdAt;
}
