package org.example.tegabus.Company;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tegabus.Booking.Booking;
import org.example.tegabus.Bus.Bus;
import org.example.tegabus.Common;
import org.example.tegabus.Route.Route;
import org.example.tegabus.Schedule.Schedule;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company extends Common {

    @Column(unique = true, nullable = false)
    private String name;
    private String address;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private CompanyStatus status;
    private LocalDateTime registrationDate;
    boolean isActive;

    @OneToMany(mappedBy = "company")
    @JsonManagedReference
    private List<Bus> buses;

    @OneToMany(mappedBy = "company")
    @JsonManagedReference
    private List<Route> routes;

    @OneToMany(mappedBy = "company")
    @JsonManagedReference
    private List<Schedule> schedules;



}
