package payments.mapper;

import org.springframework.stereotype.Component;
import payments.model.Payment;
import payments.model.dto.PaymentRequestDTO;
import payments.model.dto.PaymentResponseDTO;

import java.time.LocalDateTime;

@Component
public class PaymentMapper {

    public Payment toEntity(PaymentRequestDTO dto) {
        if (dto == null) return null;

        Payment payment = new Payment();
        payment.setBookingId(dto.bookingId());
        payment.setBookingReference(dto.bookingReference());
        payment.setGuestId(dto.guestId());
        payment.setGuestName(dto.guestName());
        payment.setPaymentMethod(dto.paymentMethod());
        payment.setPaymentType(dto.paymentType());
        payment.setAmount(dto.amount());
        payment.setCurrency(dto.currency());
        payment.setPaymentStatus("PENDING");
        payment.setCreatedAt(LocalDateTime.now());

        return payment;
    }

    public PaymentRequestDTO toRequestDTO(Payment payment) {
        if (payment == null) return null;

        return new PaymentRequestDTO(
                payment.getBookingId(),
                payment.getBookingReference(),
                payment.getGuestId(),
                payment.getGuestName(),
                payment.getPaymentMethod(),
                payment.getPaymentType(),
                payment.getAmount(),
                payment.getCurrency()
        );
    }

    public PaymentResponseDTO toResponseDTO(Payment payment) {
        if (payment == null) return null;

        return new PaymentResponseDTO(
                payment.getId(),
                payment.getBookingId(),
                payment.getBookingReference(),
                payment.getGuestId(),
                payment.getGuestName(),
                payment.getPaymentMethod(),
                payment.getPaymentType(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getTransactionId(),
                payment.getPaymentStatus(),
                payment.getRefundReason(),
                payment.getPaymentDate(),
                payment.getCreatedAt()
        );
    }
}
