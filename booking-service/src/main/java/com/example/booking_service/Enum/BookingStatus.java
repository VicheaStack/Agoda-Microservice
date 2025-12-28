// booking-service/src/main/java/com/hotel/booking/enums/BookingStatus.java
package com.example.booking_service.Enum;

public enum BookingStatus {
    PENDING,        // Booking created but not confirmed
    CONFIRMED,      // Payment received, booking confirmed
    CHECKED_IN,     // Guest has checked in
    CHECKED_OUT,    // Guest has checked out
    CANCELLED,      // Booking cancelled before check-in
    NO_SHOW,        // Guest didn't show up
    REFUNDED        // Booking cancelled and refunded
}