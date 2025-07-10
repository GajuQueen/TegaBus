package org.example.tegabus.schedule.ScheduleDtos;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Departure time is required")
    @Future(message = "Departure time must be in the future")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time is required")
    @Future(message = "Arrival time must be in the future")
    private LocalDateTime arrivalTime;

    @NotNull(message = "Status is required")
    private ScheduleStatus status;

    @NotNull(message = "Travel date is required")
    @FutureOrPresent(message = "Travel date must be today or in the future")
    private LocalDate travelDate;

    @Min(value = 1, message = "Available seats must be at least 1")
    private int availableSeats;

    private boolean isActive;

    @NotNull(message = "Bus ID is required")
    private UUID busId;

    @NotNull(message = "Company ID is required")
    private UUID companyId;

    @NotNull(message = "Route ID is required")
    private UUID routeId;
}
