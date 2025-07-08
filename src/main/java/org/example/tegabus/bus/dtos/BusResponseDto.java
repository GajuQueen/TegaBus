package org.example.tegabus.bus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.tegabus.bus.BusStatus;
import org.example.tegabus.route.routeDtos.RouteResponseDto;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusResponseDto {
    private UUID id;
    private String plateNumber;
    private BusStatus status;
    private RouteResponseDto route;
    private String driverName;
}
