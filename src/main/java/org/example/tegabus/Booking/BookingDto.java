package org.example.tegabus.Booking;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private String passengerName;
    private String passengerEmail;
    private String passengerPhone;

    private Double amountPaid;
    private LocalDate travelDate;
    private LocalDateTime bookingTime;

    private String seatNumber;
    private String ticketCode;
    private String qrCodeData;

    private UUID busId;
    private UUID ScheduleId;
    private UUID UserId;
    private String currency;
}


