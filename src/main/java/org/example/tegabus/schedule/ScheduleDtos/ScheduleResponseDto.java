package org.example.tegabus.schedule.ScheduleDtos;

import lombok.Builder;
import lombok.Data;
import org.example.tegabus.route.routeDtos.RouteResponseDto;
import org.example.tegabus.schedule.ScheduleStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ScheduleResponseDto {
    private UUID id;
    private String plateNumber;
    private RouteResponseDto route;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String driverName;
    private ScheduleStatus status;
}


