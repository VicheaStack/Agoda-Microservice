package payments.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponseDTO(

        Long id,
        Long bookingId,
        String bookingReference,
        Long guestId,
        String guestName,
        String paymentMethod,
        String paymentType,
        BigDecimal amount,
        String currency,
        String transactionId,
        String paymentStatus,
        String refundReason,
        LocalDateTime paymentDate,
        LocalDateTime createdAt

) {
}
