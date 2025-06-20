package org.example.tegabus.Route;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tegabus.Bus.Bus;
import org.example.tegabus.Common;
import org.example.tegabus.Company.Company;
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
    private Double distanceInKm;
    private int durationInMinutes;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private Company company;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    @JsonBackReference
    private Bus bus;

    @OneToMany(mappedBy = "route")
    @JsonManagedReference
    private List<Schedule> schedule;


}
