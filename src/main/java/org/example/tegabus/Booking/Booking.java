package org.example.tegabus.Booking;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tegabus.bus.Bus;
import org.example.tegabus.Common;
import org.example.tegabus.route.Route;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking extends Common {
    @Column(unique = true)
    private int seatNumber;
    private BookingStatus status;
//    SUPPOSED TO BE TO USER ENTITY
//    @Column(nullable = false)
//    private String passengerName;
//    @Column(nullable = false)
//    private String passengerEmail;
//    private String phoneNumber;
//    private LocalDate travelDate;
    private LocalDateTime bookingTime;
//    SUPPOSED TO BE TO PAYMENT
//    private Double amountPaid;
//    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    @JsonBackReference
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "route")
    @JsonBackReference
    private Route route;



}
