package org.example.tegabus.Schedule;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tegabus.booking.Booking;
import org.example.tegabus.Common;
import org.example.tegabus.bus.Bus;
import org.example.tegabus.company.Company;
import org.example.tegabus.route.Route;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Schedule extends Common {
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private LocalDate travelDate;
    private int availableSeats;
    boolean isActive;
    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private Company company;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    @JsonBackReference
    private Bus bus;
    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;

    @ManyToOne
    @JoinColumn(name = "route_id")
    @JsonBackReference
    private Route route;
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Booking> bookings;

}
