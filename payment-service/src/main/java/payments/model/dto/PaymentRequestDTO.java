package payments.model.dto;

import java.math.BigDecimal;

public record PaymentRequestDTO(

        Long bookingId,
        String bookingReference,
        Long guestId,
        String guestName,
        String paymentMethod,
        String paymentType,
        BigDecimal amount,
        String currency
) {
}
