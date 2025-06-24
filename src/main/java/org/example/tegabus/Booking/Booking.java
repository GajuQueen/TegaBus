package org.example.tegabus.Booking;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tegabus.Bus.Bus;
import org.example.tegabus.Common;
import org.example.tegabus.Schedule.Schedule;
import org.example.tegabus.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking extends Common {
    @Column(unique = true)
    private String seatNumber;
    private BookingStatus status;
    @Column(nullable = false)
    private String passengerName;
    @Column(nullable = false)
    private String passengerEmail;
    private String phoneNumber;
    private LocalDate travelDate;
    private LocalDateTime bookingTime;
    private Double amountPaid;
    private String currency;
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    @JsonBackReference
    private Bus bus;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
    @Column(nullable = false, unique = true)
    private String ticketCode;
    @Column(columnDefinition = "TEXT")
    private String qrCodeData;




}
