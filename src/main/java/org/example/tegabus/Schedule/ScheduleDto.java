package org.example.tegabus.Schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.tegabus.Booking.Booking;
import org.example.tegabus.Bus.Bus;
import org.example.tegabus.Company.Company;
import org.example.tegabus.Route.Route;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private LocalDate travelDate;
    private int availableSeats;
    boolean isActive;
    private UUID busId;
    private UUID companyId;
    private UUID routeId;

}
