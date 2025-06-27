package org.example.tegabus.schedule.ScheduleDtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ScheduleResponseDto {
    private UUID id;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private LocalDate travelDate;
    private int availableSeats;
    boolean isActive;
    private UUID busId;
    private UUID companyId;
    private UUID routeId;
}
