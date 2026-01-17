package payments.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import payments.model.Payment;
import payments.model.dto.PaymentRequestDTO;
import payments.model.dto.PaymentResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
public interface PaymentService {

    Payment create(Payment payment);
    Optional<Payment> findById(Long id);
    void deleteById(Long id);
    Page<Payment> findAll(Pageable pageable);

    Page<Payment> findByPaymentStatus(String paymentStatus, Pageable pageable);

//   // Page<Payment> findByPaymentStatus(String paymentStatus);
//    Payment processPayment(PaymentRequestDTO request);
//    Payment refundPayment(Long paymentId, String reason);
//    Payment updatePaymentStatus(Long paymentId, String status);
////    PaymentResponseDTO getPaymentDetails(Long id);
////    Page<PaymentResponseDTO> searchPayments(PaymentSearchCriteria criteria, Pageable pageable);
////    PaymentStatisticsDTO getPaymentStatistics();
////    void sendPaymentNotification(Long paymentId);
////    Payment retryFailedPayment(Long paymentId);
}
