package org.example.tegabus.bus;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tegabus.booking.Booking;
import org.example.tegabus.Common;
import org.example.tegabus.company.Company;
import org.example.tegabus.route.Route;
import org.example.tegabus.Schedule.Schedule;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bus extends Common {
    @Column(unique = true, nullable = false)
    private String plateNumber;
//    private int capacity;
    private BusModel model;
    @Column(nullable = false)
    private BusStatus status;
    @Column(nullable = false)
    private BusType type;
    @Column(nullable = false)
    private String driverName;
    @Column(unique = true, nullable = false)
    private String driverPhoneNumber;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private Company company;

    @OneToMany(mappedBy = "bus")
    @JsonManagedReference
    private List<Route> routes;

    @OneToMany(mappedBy = "bus")
    @JsonManagedReference
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "bus")
    @JsonManagedReference
    private List<Booking> bookings;


}
