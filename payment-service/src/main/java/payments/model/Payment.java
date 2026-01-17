package payments.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "payments")
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "booking_id", nullable = false)
    private Long bookingId;
    
    @Column(name = "booking_reference", length = 50)
    private String bookingReference;
    
    @Column(name = "guest_id", nullable = false)
    private Long guestId;
    
    @Column(name = "guest_name", length = 100)
    private String guestName;
    
    @Column(name = "payment_method", length = 30)
    private String paymentMethod;
    
    @Column(name = "payment_type", length = 30)
    private String paymentType;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    
    @Column(length = 3)
    private String currency = "USD";
    
    @Column(name = "transaction_id", length = 100)
    private String transactionId;
    
    @Column(name = "payment_status", length = 20)
    private String paymentStatus;
    
    @Column(name = "refund_reason", columnDefinition = "TEXT")
    private String refundReason;
    
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void generateTransactionId(){
        if(this.transactionId == null){
            this.transactionId = UUID.randomUUID().toString();
        }
        if(this.paymentDate == null){
            this.paymentDate = LocalDateTime.now();
        }
    }

}