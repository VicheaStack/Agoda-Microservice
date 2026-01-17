package payments.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payments.mapper.PaymentMapper;
import payments.model.Payment;
import payments.model.dto.PaymentRequestDTO;
import payments.model.dto.PaymentResponseDTO;
import payments.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    public PaymentController(PaymentService paymentService,
                             PaymentMapper paymentMapper) {
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
    }

    // ---------------- CREATE ----------------

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> makePayment(
            @RequestBody PaymentRequestDTO paymentRequestDTO) {

        log.info("POST /payments - create payment request received");

        Payment entity = paymentMapper.toEntity(paymentRequestDTO);
        Payment payment = paymentService.create(entity);
        PaymentResponseDTO responseDTO = paymentMapper.toResponseDTO(payment);

        log.info("POST /payments - payment created paymentId={}", payment.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // ---------------- DELETE ----------------

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {

        log.info("DELETE /payments/{} - delete request received", id);

        paymentService.deleteById(id);

        log.info("DELETE /payments/{} - payment deleted", id);

        return ResponseEntity.noContent().build();
    }

    // ---------------- FIND ALL ----------------

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("GET /payments?page={}&size={}", page, size);

        Page<Payment> all = paymentService.findAll(PageRequest.of(page, size));

        List<PaymentResponseDTO> result = all.getContent()
                .stream()
                .map(paymentMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(result);
    }

    // ---------------- FIND BY STATUS ----------------

    @GetMapping("/status/{paymentStatus}")
    public ResponseEntity<List<PaymentResponseDTO>> findByStatus(
            @PathVariable String paymentStatus,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("GET /payments/status/{}?page={}&size={}",
                paymentStatus, page, size);

        Page<Payment> payments =
                paymentService.findByPaymentStatus(paymentStatus, PageRequest.of(page, size));

        List<PaymentResponseDTO> result = payments.getContent()
                .stream()
                .map(paymentMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(result);
    }
}
