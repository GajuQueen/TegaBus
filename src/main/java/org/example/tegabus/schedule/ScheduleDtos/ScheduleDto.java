package org.example.tegabus.schedule.ScheduleDtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.tegabus.schedule.ScheduleStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private ScheduleStatus status;
    private LocalDate travelDate;
    private int availableSeats;
    boolean isActive;
    private UUID busId;
    private UUID companyId;
    private UUID routeId;
}
