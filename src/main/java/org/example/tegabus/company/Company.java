package org.example.tegabus.company;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tegabus.bus.Bus;
import org.example.tegabus.Common;
import org.example.tegabus.route.Route;
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
    private String companyName;
    private String companyAddress;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanyStatus status;
    @Column(nullable = false)
    private LocalDateTime registration;


    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Bus> buses;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Route> routes;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Schedule> schedules;
}
