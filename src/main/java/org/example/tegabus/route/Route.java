package org.example.tegabus.route;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tegabus.booking.Booking;
import org.example.tegabus.bus.Bus;
import org.example.tegabus.Common;
import org.example.tegabus.company.Company;
import org.example.tegabus.Schedule.Schedule;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "routes")
public class Route extends Common {
    @Column(nullable = false)
    private String origin;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private Double distanceInKm;
    @Column(nullable = false)
    private int durationInMinutes;
    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private Company company;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    @JsonBackReference
    private Bus bus;

    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Booking> bookings;
}
