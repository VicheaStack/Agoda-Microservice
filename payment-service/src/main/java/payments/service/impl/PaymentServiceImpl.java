package payments.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import payments.exception.ResourceNotFoundException;
import payments.model.Payment;
import payments.repository.PaymentRepository;
import payments.service.PaymentService;

import java.util.Optional;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment create(Payment payment) {
        log.info("Creating payment bookingId={}, amount={}",
                payment.getBookingId(),
                payment.getAmount()
        );

        Payment savedPayment = paymentRepository.save(payment);

        log.info("Payment created successfully paymentId={}, bookingId={}",
                savedPayment.getId(),
                savedPayment.getBookingId()
        );

        return savedPayment;
    }

    @Override
    public Optional<Payment> findById(Long id) {
        log.info("Fetching payment by id={}", id);

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Payment not found id={}", id);
                    return new ResourceNotFoundException("Payment not found");
                });

        log.info("Payment found id={}, status={}",
                payment.getId(),
                payment.getPaymentStatus()
        );

        return Optional.of(payment);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting payment id={}", id);

        if (!paymentRepository.existsById(id)) {
            log.warn("Attempt to delete non-existing payment id={}", id);
            throw new ResourceNotFoundException("Payment not exist");
        }

        paymentRepository.deleteById(id);

        log.info("Payment deleted successfully id={}", id);
    }

    @Override
    public Page<Payment> findAll(Pageable pageable) {
        log.info("Fetching all payments page={}, size={}",
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

        Page<Payment> payments = paymentRepository.findAll(pageable);

        log.info("Payments fetched totalElements={}",
                payments.getTotalElements()
        );

        return payments;
    }

    @Override
    public Page<Payment> findByPaymentStatus(String paymentStatus, Pageable pageable) {
        log.info("Fetching payments by status={}, page={}, size={}",
                paymentStatus,
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

        Page<Payment> payments =
                paymentRepository.findByPaymentStatus(paymentStatus, pageable);

        log.info("Payments fetched status={}, totalElements={}",
                paymentStatus,
                payments.getTotalElements()
        );

        return payments;
    }
}
