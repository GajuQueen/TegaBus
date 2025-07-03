package org.example.tegabus.schedule.ScheduleDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScheduleDto {
        private LocalDateTime departureTime;
        private LocalDateTime arrivalTime;
        private LocalDate travelDate;
        private int availableSeats;
        boolean isActive;
        private UUID busId;
        private UUID companyId;
        private UUID routeId;

    }
}
